package or.itschool.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import or.itschool.mapper.BoardMapper;
import or.itschool.model.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardDAOTest {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardDAO;

	@Test
	public void insertTest() throws Exception {

		for (int i = 1; i < 3000; i++) {
			BoardVO vo = new BoardVO();
			vo.setTitle(i + "번째 테스트 게시물!!");
			vo.setContent(i + "번째 게시물 내용입니다.");
			vo.setWriter("user" + i);
			boardDAO.insert(vo);
			log.info(boardDAO);
		}
	}

	@Test
	public void selectOneTest() throws Exception {
		log.info(boardDAO.getArticle(100).toString() + "\n");
	}

	@Test
	public void updateTest() throws Exception {
		BoardVO article = new BoardVO();

		article.setBoardNo(1);
		article.setTitle("글 김세열 제목 수정~!~!");
		article.setContent("글 김세열 내용 수정~!~!");
		boardDAO.update(article);

		log.info(boardDAO.getArticle(1).toString() + "\n");
	}
	
	@Test
	public void deleteTest() throws Exception {
		boardDAO.delete(10);
	}
	
	@Test
	public void selectAllTest() throws Exception {
		List<BoardVO> articles = boardDAO.getAllArticles();
		for (BoardVO article : articles) {
			log.info(article.toString());
		}
	}
}
