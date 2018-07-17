package jp.te4a.zoo.spring.boot.CallCenterSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.te4a.zoo.spring.boot.CallCenterSystem.service.AccessLogService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.ClassService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.CustomerCallService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.CustomerService;
import jp.te4a.zoo.spring.boot.CallCenterSystem.service.SystemUserService;

/*
 * メインコントローラ
 * 問い合わせ情報表示まで対応
 * アドレスは operation
 */

@Controller
@RequestMapping("operation")
public class MgSysMainController {

	@Autowired
	CustomerService customerService;

	@Autowired
	ClassService classService;

	@Autowired
	CustomerCallService customerCallService;

	@Autowired
	SystemUserService systemUserService;

	@Autowired
	AccessLogService accessLogService;

	// ログイン後に表示
	@RequestMapping
	String list() {
		// 初回ログインであればページパスワード変更画面を表示
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uId = userDetails.getUsername();

		// ログイン成功時アクセスログの成功フラグを1にする
		int logId = accessLogService.searchLastAccessByUId(uId);
		accessLogService.updateAccResult(logId);

		if(systemUserService.getFirstFlag(uId) == 1) {
			return "redirect:password";
		}
		else {
			return "operation/search";
		}
	}

	// 顧客問い合わせ情報検索実行( "operation/searching" )
	@RequestMapping("searching")
	String saerchResult(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName, @RequestParam("tel") String tel, @RequestParam("address") String address, Model model) throws Exception {

		// 名字、名前、電話番号、住所から顧客IDを検索
		String cId = customerService.searchCustomerId(lastName, firstName, tel, address);
		model.addAttribute("cId", cId); // 顧客IDをmodelに追加

		// 顧客IDから顧客名取得
		String customerName = customerService.findNameByUid(cId);
		model.addAttribute("customerName", customerName); // 顧客の名前をmodelに追加

		// 顧客IDが取得できなければエラー
		if(customerName == null) return "forward:?error";

		// 顧客IDから顧客の問い合わせ情報全検索
		String[][] callData = customerCallService.findAllDataByCid(cId);
		model.addAttribute("callData", callData); // 検索結果をmodelに追加

		return "operation/inquiry-list";
	}

	// 顧客問い合わせ情報絞り込み( "operation/refine" )
	@RequestMapping("refine")
	String refineResult(@RequestParam("cId") String cId, @RequestParam("customerName") String customerName, @RequestParam("year") String year, @RequestParam("month") String month, @RequestParam("day") String day, @RequestParam("range") String range, @RequestParam("category") String category, Model model) {

		// 顧客IDと名前をmodelに追加
		model.addAttribute("cId", cId);
		model.addAttribute("customerName", customerName);
		
		// 日付を成型
		String inDay = year + "/" + month + "/" + day;

		String[][] refinedCallData;

		if(!category.equals("すべて")) {
			// カテゴリの指定があった場合
			// カテゴリのIDを取得
			String clsId = classService.findIdByName(category);

			// 日付の範囲で分岐
			if(range.equals("以前")) 
				refinedCallData = customerCallService.findByClassAndBeforeDate(cId, clsId, inDay);
			else 
				refinedCallData = customerCallService.findByClassAndAfterDate(cId, clsId, inDay);
		}
		else{
			// カテゴリの指定がなかった場合
			// 日付の範囲で分岐
			if(range.equals("以前")) 
				refinedCallData = customerCallService.findByBeforeDate(cId, inDay);
			else 
				refinedCallData = customerCallService.findByAfterDate(cId, inDay);
		}

		// 検索結果をmodelに追加
		model.addAttribute("callData", refinedCallData);

		return "operation/inquiry-list";
	}
	
	// 問い合わせの詳細表示 ( "operation/details" )
	@RequestMapping("details")
	String details(@RequestParam("cId") String cId, @RequestParam("customerName") String customerName, @RequestParam("selectedCallId") String selectedCallId, Model model) {
		
		// 顧客IDと名前をmodelに追加
		model.addAttribute("cId", cId);
		model.addAttribute("customerName", customerName);
		
		// 問い合わせIDから一件のデータを取得
		String[][] selectedCallData = customerCallService.findDataById(selectedCallId);
		
		// 検索結果をmodelに追加
		model.addAttribute("selectedCallData", selectedCallData);
		
		return "operation/sub_details";
	}
}
