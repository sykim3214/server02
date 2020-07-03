package or.itschool.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import or.itschool.mapper.BoardMapper;
import or.itschool.model.BoardVO;

public class BoardDAO implements BoardMapper {

	private final SqlSessionFactory sqlSession;
	private static final String NAMESPACE = "BoardMapper";

	@Autowired // 의존성 주입 어노테이션
	public BoardDAO(SqlSession sqlSession) {
		this.sqlSession = (SqlSessionFactory) sqlSession;
	}

	@Override
	public void insert(BoardVO article) throws Exception {
		((SqlSession) sqlSession).insert(NAMESPACE + ".insert", article);
	}

	// 2. 하나의 게시글 조회 기능 메서드 -하나를 조회하려면 프라이머리키(글 번호)를 가져
	@Override
	public BoardVO getArticle(int boardNo) throws Exception {
		System.out.println("게시글 번호 :" + boardNo);
		return ((SqlSession) sqlSession).selectOne(NAMESPACE + ".getArticle", boardNo);
	}

	@Override
	public void update(BoardVO article) throws Exception {
		((SqlSession) sqlSession).update(NAMESPACE + ".update", article);
	}

	@Override
	public void delete(int boardNo) throws Exception {
		((SqlSession) sqlSession).delete(NAMESPACE + ".delete", boardNo);
	}

	@Override
	public List<BoardVO> getAllArticles() throws Exception {
		return ((SqlSession) sqlSession).selectList(NAMESPACE + ".getAllArticles");
	}

}
