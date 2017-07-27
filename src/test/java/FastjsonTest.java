/**
 * Created by Administrator on 2017/7/21.
 */

import com.alibaba.fastjson.JSON;

/**
 * @author lierl
 * @create 2017-07-21 14:08
 **/
public class FastjsonTest {
	public static void main(String[] args) {
		LoanApply loan = new LoanApply();
		loan.setAmount(1.0);
		loan.setProductId("1111111");
		System.out.println(JSON.toJSONString(loan));
	}
}

class LoanApply{
	private Double amount;
	private String productId;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}

