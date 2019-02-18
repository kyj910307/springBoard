package com.spring.myapp.util;

public class BoardPager {
	
	// 페이지 개당 게시물 수
	public static final int PAGE_SCALE = 10;
	// 화면당 페이지수
	public static final int BLOCK_SCALE = 10;
	
	private int curPage; // 현재페이지
	private int prevPage; // 이전페이지
	private int nextPage; // 다음페이지
	private int totPage; // 전체 페이지 갯수
	private int totBlock; // 전체 페이지 블록 갯수
	private int curBlock; // 현재 페이지 블록
	private int prevBlock; // 이정페이지블록
	private int nextBlock; // 다음페이지 블록
	
	private int pageBegin; // #{start}
	private int pageEnd; // #{end}
	// [이전] blockBegin
	private int blockBegin;
	private int blockEnd;
	
	// 생성자
	// BaordPager(레코드갯수,현재페이지 번호)
	public BoardPager(int count, int curPage) {
		curBlock = 1; // 현재페이지 블록번호
		this.curPage = curPage; // 현재페이지설정
		setTotPage(count); // 전체페이지 갯수 계산
		setPageRange();
		setTotBlock(); // 전체페이지 블록갯수 계산
		setBlockRange(); // 페이지블록의 시작 끝 번호 계산
	}
	
	// 페이지블록의 시작 끝 번호 계산
	public void setBlockRange(){
		// 현재 페이지가 몇번째 페이지블록에 속하는지 계산
		/*
			현재 페이지 블록을 구하는 식 
			현재블록은 = 현재페이지 -1 Math.ceil(올림/반환형은 double) 현재페이지를 계산한후
			화면당 보여줄 페이지수(블록) =BLOCK_SCALE[상수로 만들어놓은 페이지수(블록수) 10을나눈다]
			그후 +1 을 하면 현재 보고있는 페이지가 몇번째 블록에 속하는지 알수있다 
		*/
		curBlock = (int)Math.ceil((curPage-1)/BLOCK_SCALE)+1;
		// 현재 페이지 블록의 시작/끝 번호 를 계산
		/*
			몇번쨰 블록인지 알수있는 curBlock -1 을 하고 block_scale 값을 곱한후 +1 을 하면
			현재 페이지 블록의 시작,끝 번호를 알수있다 
		*/
		blockBegin = (curBlock-1)*BLOCK_SCALE+1;
		// 페이지블록의 끝번호
		/*
			블록의 시작,끝번호를 알수있는 blockBegin 과 화면당 페이지수를 알수있는BLOCK_SCALE
			를 더해주고 -1 을 하면 블록의 끝번호를 알수있다.
		*/
		blockEnd = blockBegin+BLOCK_SCALE-1;
		// 마지막블록이 범위를 초과하지 않게 하기위한 계산 식
		/*
			블록의 끝번호 가 총페이지보다 크지 않을때 
			블록끝번호[blockEnd] = 총페이지[totPage]
			블록끝번호는 총페이지의 수가 된다.[sysout 으로 테스트]
		*/
		if(blockEnd > totPage){
			blockEnd = totPage;
		}
		// 이전을 눌렀을때 이동할페이지 번호
		/*
			이전페이지로 가기를 눌렀을 경우 
			현재페이지[curPage] 가 1 페이지일 경우는 1을 출력하고 1이 아니면
			현재페이지블록(curBlock) 의 -1 을빼면 현재 보고있는 페이지의 블록 번호의 이전번호 출력이되는데
			block_scale(10) 을 곱한다. [sysout 으로 왜 곱해야하는지 알아보기]
		*/
		prevPage = (curPage == 1)?1:(curBlock-1)*BLOCK_SCALE;
		// 다음 을 눌렀을때 이동할페이지번호
		/*
			sysout 으로 알아보기
		*/
		nextPage = curBlock > totBlock ?(curBlock*BLOCK_SCALE):(curBlock*BLOCK_SCALE)+1;
		
		// 마지막 페이지가 범위를 초과하지 않도록 처리
		if(nextPage>=totPage){
			nextPage = totPage;
		}
	}
	
	public void setPageRange(){
		// where rn BETWEEN  #{start} AND #{end}
		// 시작번호 = (현재페이지-1)*페이지당 게시물수 +1
		pageBegin = (curPage-1)*PAGE_SCALE+1;
		// 끝번호 = 시작번호+페이지당게시물수-1
		pageEnd = pageBegin+PAGE_SCALE-1;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotPage() {
		return totPage;
	}

	public void setTotPage(int count) {
		// Math.ceil(실수) 올림처리
		totPage = (int)Math.ceil(count*1.0/PAGE_SCALE);
	}

	public int getTotBlock() {
		return totBlock;
	}
	
	// 페이지블록의 갯수계산(총 100페이지 라면 10개의 블록 표시)
	public void setTotBlock() {
		// 전체페이지 갯수 /10
		// 91/10 => 9.1 => 10개
		totBlock = (int)Math.ceil(totPage/BLOCK_SCALE);
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}	
}
