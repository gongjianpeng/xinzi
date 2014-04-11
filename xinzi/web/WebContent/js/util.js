/***
 * 贷款人类型
 * @param v
 * @param m
 * @return
 */
function formatLoanerTypeColor(v, m){
	if (v=="true") {
		m.attr="style='color:red;'";
		return "主贷人";
	} else {                  
		m.attr="style='color:green'";
		return "次贷人";
	}
}
