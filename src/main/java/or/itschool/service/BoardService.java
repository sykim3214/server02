package or.itschool.service;

import java.util.List;

import or.itschool.model.BoardVO;

public interface BoardService { 
	
	// 1. 게시글 등록 기능 메서드 - 게시글을 하나 가져와서 등록
	void insert(BoardVO article) throws Exception;

	// 2. 하나의 게시글 조회 기능 메서드
	BoardVO getArticle(int boardNo) throws Exception;

	// 3. 게시물 수정 기능 메서드
	void update(BoardVO article) throws Exception;

	// 4. 게시물 삭제 기능 메서드
	void delete(int boardNo) throws Exception;

	// 5. 모든 게시물 조회 기능 메서드
	List<BoardVO> getAllArticles() throws Exception;

}
