package items;

import java.util.ArrayList;

import dao.CartCrud;

public class Cart { //장바구니 역할의 클래스
	//변수(장바구니 주인, 상품 코드, 상품 갯수)
	//상품 코드는 여러개가 가능해야 한다. ArrayList<String>
	//상품 갯수도 여러개가 가능해야 한다. ArrayList<Integer>
	//장바구니 주인, String id
	public Cart(String id) {this.id = id;} //생성자
	
	private String id;
	private ArrayList<String> codeList = new ArrayList<String>();
	private ArrayList<Integer> numList = new ArrayList<Integer>();
	
	public ArrayList<String> getCodeList()	{
		return codeList;
	}
	public ArrayList<Integer> getNumList() 	{
		return numList;
	}
	
	//메서드(장바구니에 담기, 장바구니에서 삭제, 장바구니에서 변경)
	public void modifyItem(String code, Integer num) {//장바구니에서 상품의 갯수를 수정하는 메서드
		//code에 있는 상품번호와 일치하는 상품번호를 codeList에서 찾는다
		//해당 상품번호의 상품갯수를 num에 있는 갯수로 변경한다.
		for(int i=0; i < codeList.size(); i++) {
			if(codeList.get(i).equals(code)) { //i번째 상품번호와 code에 있는 상품번호가 같은 경우
				numList.set(i, num); //i번째 상품의 갯수를 num에 있는 상품갯수로 변경한다.
				
				//데이터베이스와 연동 시작
				CartCrud dao = new CartCrud();
				Cart_tbl dto = new Cart_tbl();
				dto.setId(id); dto.setCode(code); dto.setNum(num);
				dao.updateCart(dto);
				//데이터베이스와 연동 끝
				
				return; //메서드 종료
			}
		}
	}
	
	public void deleteItem(String code) { //장바구니에서 상품의 갯수를 수정하는 메서드
		for(int i=0; i < codeList.size(); i++) {
			if(codeList.get(i).equals(code)) { //장바구니의 상품코드와 삭제하려는 상품코드가 일치하는 경우
				codeList.remove(i); //i번째 상품코드를 삭제한다.
				numList.remove(i); //i번쨰 상품갯수를 삭제한다.
				
				//데이터베이스 연동 시작
				CartCrud dao = new CartCrud();
				Cart_tbl dto = new Cart_tbl();
				dto.setId(id); dto.setCode(code);
				dao.deleteCart(dto);
				//데이터베이스 연동 끝
				
				return; //메서드를 종료한다.
			}
		}
	}
	
	public void addCart(String code, Integer num) { //장바구니에 상품의 번호와 상품의 갯수를 저장하는 메서드
		//code안에 있는 상품의 번호를 codeList에 저장하기 전에 이미 해당 상품의 번호가
		//codeList에 있는지 검사한다. 만약 동일한 상품의 번호가 codeList에 있으면 갯수를 1 증가하도록 한다.
		//만약 동일한 상품의 번호가 codeList에 없으면 codeList에 상품번호를 넣고 저장. numList에는 1 저장.
		for(int i=0; i < codeList.size(); i++) { //codeList안에 있는 모든 상품 번호를 차례로 검사한다
			if(codeList.get(i).equals(code)) { //i번째 상품 번호와 code에 있는 상품번호가 같은 경우
				Integer quantity = numList.get(i); //i번째 상품 갯수를 찾는다
				int sum = quantity + num; //기존의 갯수 + num에 있는 갯수
				numList.set(i, sum); //i번째 상품의 갯수를 sum에 있는 갯수로 바꾼다.
				
				//데이터베이스와 연동 시작
				CartCrud dao = new CartCrud();
				Cart_tbl dto = new Cart_tbl();
				dto.setId(id); dto.setCode(code); dto.setNum(sum);
				dao.updateCart(dto);
				//데이터베이스와 연동 끝
				
				return; //메서드 종료
			}
		}
		
		//return이 되지 않을 경우(장바구니에 없는 상품을 담는 경우)
		codeList.add(code); numList.add(num);
		
		//데이터베이스와 연동 시작
		CartCrud dao = new CartCrud();
		Cart_tbl dto = new Cart_tbl();
		dto.setId(id); dto.setCode(code); dto.setNum(num);
		dao.addCart(dto);
		//데이터베이스와 연동 끝
	}
	
}
