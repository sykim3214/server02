package or.itschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;
import or.itschool.model.BoardVO;
import or.itschool.service.BoardService;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {

	// 컨트롤러와 서비스가 의존관계가 설정되어있으니 의존성 주입해준다. 데이터와 뷰를 연결해주는것
	@Autowired
	// @Setter(onMethod_ = @Autowired)
	private BoardService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		log.info("/board/list: GET 요청발생!");
		System.out.println("게시글 페이지 열람 요청");

		List<BoardVO> articles = service.getAllArticles();
		System.out.println("=========");
		for (BoardVO vo : articles)
			System.out.println(vo);
		System.out.println("=========");

		model.addAttribute("articles", service.getAllArticles());
		return "board/list";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		log.info("/board/write : GET 요청발생!");
		return "board/write";
	}

	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		log.info("/board/content): GET 요청발생!");
		model.addAttribute("article", service.getArticle(boardNo));
		return "board/content";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		log.info("/board/modify : GET 요청발생!");
		model.addAttribute("article", service.getArticle(boardNo));
		return "board/modify";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO article, RedirectAttributes redirectAttr) throws Exception {
		log.info("/board/write : POST 요청!");
		log.info("가져온 게시글 확인:" + article.toString());
		service.insert(article);
		redirectAttr.addFlashAttribute("message", "regSuccess");
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(BoardVO article, RedirectAttributes redirectAttr) throws Exception {
		log.info("/board/modify: POST 요청!");
		service.update(article);
		redirectAttr.addFlashAttribute("message", "modSuccess");
		return "redirect:/board/content?boardNo=" + article.getBoardNo();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("boardNo") int boardNo, RedirectAttributes redirectAttr) throws Exception {
		log.info("/board/delete : POST 요청 !");
		service.delete(boardNo);
		redirectAttr.addFlashAttribute("message", "delSuccess");
		return "redirect:/board/list";
	}
}
