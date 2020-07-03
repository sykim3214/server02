package or.itschool.model;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	//사용할 database table 컬럼과 1:1로 매핑되는 필드
	private int boardNo; 			// 글번호 board_no
	private String title;			
	private String content;
	private String writer;
	private Date regDate;
	private int viewCnt;

}
