package com.example.boardProject.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.boardProject.board.dto.BoardDTO;
import com.example.boardProject.board.dto.BoardMediaDTO;
import com.example.boardProject.board.mapper.BoardMapper;
import com.example.boardProject.board.mapper.BoardMediaMapper;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class BoardService {

	// 저장 경로 (절대 경로)
	@Value("${file.upload.path}")
	private String uploadPath;

	// 웹에서 접근할 때 사용할 상대 경로(URL)
	@Value("${file.relative.path}")
	private String relativePath;

	private final BoardMapper boardMapper;
	private final BoardMediaMapper boardMediaMapper;

	@Autowired
	public BoardService(BoardMapper boardMapper, BoardMediaMapper boardMediaMapper) {
		this.boardMapper = boardMapper;
		this.boardMediaMapper = boardMediaMapper;
	}

//	List<BoardDTO> findAll();

	// 게시글 전체 조회
	public List<BoardDTO> findAll() {

		return boardMapper.findAll();
	}

	// 게시글 정보 저장
	@Transactional // 트랜잭션화
	public boolean createBoard(BoardDTO boardDTO) {
		try {
			// 데이터 처리를 하는 코드를 ~~~~
			int insertReuslt = boardMapper.createBoard(boardDTO);

			if (insertReuslt > 0) {
				this.saveFiles(boardDTO); // 실패 시 예외 발생 → 전체 롤백
				return true;
			} else {
				throw new RuntimeException("게시판 정보 저장 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 파일 생성 및 board_media에 파일 정보 저장 메서드
	public void saveFiles(BoardDTO boardDTO) {
		// 게시판 idx
		int boardIdx = boardDTO.getIdx();
		// 파일 리스트
		List<MultipartFile> uploadFiles = boardDTO.getUploadFiles();

		if (uploadFiles == null || uploadFiles.isEmpty()) {
			return;
		}

		File dir = new File(uploadPath);
		if (!dir.exists()) {
			dir.mkdirs(); // 업로드 경로가 없다면 생성
		}

		// 파일 리스트 반복
		for (MultipartFile file : uploadFiles) {
			// 파일명 추출
			// UUID 처리
			// DB에 파일 정보 저장
			// 실제 파일 저장
		}

		// 파일 리스트 반복
		for (MultipartFile file : uploadFiles) {

			// 사용자가 업로드한 원본 파일명 (예: "dog.jpg", "resume.pdf")
			String originalName = file.getOriginalFilename();

			// 업로드 확장자 검사 코드 ~
			// 파일 크기 제한 코드 ~

			// UUID(범용 고유 식별자)를 생성하여 중복되지 않는 고유한 이름 생성
			String uuid = UUID.randomUUID().toString();
			// 확장자 추출 (예: ".jpg", ".png")
			String extension = originalName.substring(originalName.lastIndexOf("."));
			// 최종 저장 파일명: UUID + 확장자 (예: "550e8400-e29b-41d4...jpg")
			String newFileName = uuid + extension;
			// 실제 파일이 저장될 전체 경로 ( 서버에서 파일을 실제로 저장할 + uuid 파일 명)
			String fullFilePath = uploadPath + newFileName;
			// 웹에서 접근할 파일의 상대 경로 ( webConfig에서 지정한 상대 경로 + uuid 파일 명)
			String relativeFilePath = relativePath + newFileName;
			// 파일의 타입 ( 이미지 image, 동영상 video , 음성 mp3) 을 리턴하는 메서드 호출
			String mediaType = getMediaType(file);

			BoardMediaDTO boardMediaDTO = new BoardMediaDTO();
			boardMediaDTO.setBoardIdx(boardIdx); // 게시판 idx (게시판 파일 idx 아님!!)
			boardMediaDTO.setMediaPath(relativeFilePath); // 웹에서 접근하는 상대 경로 (relativePath + uuid파일 명)
			boardMediaDTO.setMediaType(mediaType); // 파일의 타입 (image,video,mp3)
			boardMediaDTO.setOriginalName(originalName);

			int insertResult = boardMediaMapper.insertMedia(boardMediaDTO);

			if (insertResult <= 0) {
				throw new RuntimeException("파일 정보 저장 실패");
			}

			// 파일 저장 (실제 디스크에 저장)
			try {
				File dest = new File(fullFilePath);
				file.transferTo(dest);
			} catch (IOException e) {
				throw new RuntimeException("파일 저장 실패: " + originalName, e);
			}

		}

	}

	// 파일 타입 확인
	private String getMediaType(MultipartFile file) {
		String contentType = file.getContentType();
		if (contentType != null) {
			if (contentType.startsWith("image")) {
				return "image";
			}
			if (contentType.startsWith("video")) {
				return "video";
			}
			if (contentType.startsWith("audio")) {
				return "audio"; // mp3 포함
			}
		}
		return "unknown";
	}

	// 단일 게시글 조회
	public BoardDTO findBoardById(int idx) {
		try {
			BoardDTO board = boardMapper.findBoardById(idx);

			return board; // null 혹은 게시글 정보
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 게시글에 달린 미디어 정보 조회 메서드
	public List<BoardMediaDTO> findMediaByBoardIdx(int boardIdx) {
		try {
			return boardMediaMapper.findMediaByBoardIdx(boardIdx); // null 혹은 파일 정보
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 게시글 수정
	public boolean updateBoard(BoardDTO boardDTO) {
		try {
			int updateResult = boardMapper.updateBoard(boardDTO);
			return updateResult > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 게시글 삭제 메서드
	@Transactional
	public boolean deleteBoard(int boardIdx) {
		try {
			// 1. DB에서 삭제 대상 파일 목록 미리 조회
			List<BoardMediaDTO> mediaList = boardMediaMapper.findMediaByBoardIdx(boardIdx);

			// 2. 게시글 삭제
			int result = boardMapper.deleteBoard(boardIdx);
			if (result <= 0) {
				throw new RuntimeException("게시글 삭제 실패");
			}

			// 3. 파일 삭제 메서드 호출
			deleteFiles(mediaList);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("게시글 삭제 중 오류 발생", e);
		}
	}

	// 실제 파일 삭제 메서드 (별도 메서드로 분리)
	private void deleteFiles(List<BoardMediaDTO> mediaList) {
		for (BoardMediaDTO media : mediaList) {
			String filePath = uploadPath + media.getMediaPath().replace(relativePath, "");
			File file = new File(filePath);

			if (file.exists() && !file.delete()) {
				System.err.println("[경고] 파일 삭제 실패: " + filePath);
			}
		}
	}

	// 파일 다운로드
	public void downloadFileByIdx(int idx, HttpServletResponse response) {
		try {
			// 1. 파일 정보 조회 (DB)
			BoardMediaDTO fileInfo = boardMediaMapper.findMediaByIdx(idx); // 예: DB에서 파일 조회
			if (fileInfo == null) {
				throw new IllegalArgumentException("해당 파일이 존재하지 않습니다.");
			}
			String filePath = uploadPath + fileInfo.getMediaPath().replace(relativePath, ""); // 저장된 실제 경로
			String originalName = fileInfo.getOriginalName(); // 사용자가 업로드한 이름

			File file = new File(filePath);

			// 2. 파일 존재 여부 확인
			if (!file.exists()) {
				throw new FileNotFoundException("파일을 찾을 수 없습니다.");
			}

			// 3. 응답 헤더 설정
			response.setContentType("application/octet-stream");

			// 파일명이 한글일 경우를 대비해 인코딩 처리
			String encodedName = URLEncoder.encode(originalName, "UTF-8").replaceAll("\\+", "%20");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedName + "\"");

			// 4. 파일을 바이트로 읽어서 전송
			try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
					BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());) {
				byte[] buffer = new byte[8192]; // 8KB 버퍼
				int bytesRead;
				while ((bytesRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 예외 로깅 또는 사용자 정의 에러 페이지 처리 가능
		}
	}

}
