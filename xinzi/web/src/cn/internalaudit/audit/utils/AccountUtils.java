package cn.internalaudit.audit.utils;

import java.math.BigDecimal;

/**********
 * 计算工具类
 * 
 * @author zhangyong
 * 
 */
public class AccountUtils {
	/*********
	 * 四舍五入
	 * 
	 * @param money
	 *            金额
	 * @param length
	 *            小数位数
	 * @return 四舍王入值
	 */
	public static Double Round(double money, int length) {
		BigDecimal bigDecimal = new BigDecimal(money);
		return bigDecimal.setScale(length, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**********
	 * 计算每期还款 公式：每月还款额 =贷款金额 *（月利率＋管理费率)* 期数+ 贷款金额 ）/期数
	 * 
	 * @param loanMoney
	 *            贷款金额
	 * @param timeLimit
	 *            期数
	 * @param lendingRate
	 *            月利率
	 * @param managerFeeRate
	 *            管理费利率
	 * @return 每期还款额
	 */
	public static Double everyGiveBack(Double loanMoney, Integer timeLimit,
			Double lendingRate, Double managerFeeRate) {
		// 月利率＋管理费率
		double lendingRateAndManagerFeeRate = (lendingRate / 100)
				+ (managerFeeRate / 100);

		double everyGiveBack = (loanMoney * (lendingRateAndManagerFeeRate)
				* timeLimit + loanMoney)
				/ timeLimit;

		return everyGiveBack;
	}

	/*******
	 * 每月应还本金+利息
	 * 
	 * @param loanMoney
	 *            贷款金额
	 * @param timeLimit
	 *            贷款期数
	 * @param lendingRate
	 *            月利率
	 * @return 每月还款额 ，不计管理费
	 */
	public static Double everyGiveBack(Double loanMoney, Integer timeLimit,
			Double lendingRate) {
		double everGiveBack = (loanMoney * lendingRate * timeLimit / 100.00 + loanMoney)
				/ timeLimit;
		return everGiveBack;

	}

	/*********
	 * 违约金计算 计算公式： 违约金= 按贷款金额 * 纬约金费率
	 * 
	 * @param loanMoney
	 *            贷款金额
	 * @param timeLimit
	 *            期数
	 * @return 违约金
	 */
	public static Double fellBackFee(Double loanMoney, Integer timeLimit) {
		double fellBackFee = 0D;
		fellBackFee = loanMoney * (AccountUtils.fellBackRate(timeLimit) / 100);
		return fellBackFee;
	}

	/*******
	 * 计算 违约费率 = 代款期数小于等于24期 违约金费率 按百分之4计算 代款期数 大于24期 违约金费率 按百分之5计算
	 * 
	 * @param timeLimit
	 *            贷款期数
	 * @return 违约金费率
	 */
	public static Double fellBackRate(Integer timeLimit) {
		if (timeLimit <= 24) {
			return 4D;
		} else if (timeLimit > 24) {
			return 5D;
		}
		return 0D;
	}

	/*******
	 * 总管理费计算 計算公式：总管理费＝贷款金额 　×　管理费率　×　期数
	 * 
	 * @param loanMoney
	 *            贷款金额
	 * @param timeLimit
	 *            贷款期数
	 * @param managerFeeRate
	 *            管理费率
	 * @return 总管理费
	 */
	public static Double managerFee(Double loanMoney, Integer timeLimit,
			Double managerFeeRate) {
		double manageFee = loanMoney * (managerFeeRate / 100.00) * timeLimit;
		return manageFee;
	}

	/******
	 * 总利息计算 公式：（每期还款金额 - （贷款金额 *管理费率））*期数 - 贷款金额
	 * 
	 * @param loanMoney
	 *            贷款金额
	 * @param timeLimit
	 *            贷款期数
	 * @param lendingRate
	 *            月利率
	 * @param managerFeeRate
	 *            管理费率
	 * @return 总利息
	 */
	public static Double lendingTotal(Double loanMoney, Integer timeLimit,
			Double lendingRate, Double managerFeeRate) {
		double everyGiveBack = AccountUtils.everyGiveBack(loanMoney, timeLimit,
				lendingRate, managerFeeRate);
		double lendingTotal = (everyGiveBack - (loanMoney * managerFeeRate / 100.00))
				* timeLimit - loanMoney;
		return lendingTotal;
	}
	/******
	 * 延期费计算  公式：延期费 = 实际贷款金额*（月利率+管理费率）/30*延期天数
	 * @param loanMoney 实际贷款金额
	 * @param lendingRate 月利率
	 * @param managerFeeRate 管理费率
	 * @param deferDays 延期天数
	 * @return 延期费 
	 */
	public static Double deferFee(Double loanMoney, Double lendingRate,
			Double managerFeeRate, Integer deferDays){		
		return loanMoney * (lendingRate/100 + managerFeeRate/100)/30 * deferDays;
	}

	/********
	 * 如果是最后一期：利息= 每月还款额 - 本金 否则：利息= 贷款结余 * 每月递减利率
	 * 
	 * 本金计算：如果是最后一期 本金= 上期本金结余 否则：本金= 每月还款额 -利息-管理费
	 * 
	 * @return
	 */
	public static Double everyDegressionLending() {
		return 0D;
	}

}
