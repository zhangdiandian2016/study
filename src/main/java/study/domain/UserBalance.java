package study.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 
 * @author denny.zhang
 * @date 2018-09-03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBalance {
		/**
     * ID主键
     */
	 private Integer id; 
		/**
     * 姓名
     */
	 private String name; 
		/**
     * 用户表ID
     */
	 private Integer userId; 
		/**
     * 账户余额
     */
	 private BigDecimal balance; 
	}