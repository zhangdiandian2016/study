package study.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author denny.zhang
 * @date 2018-09-03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
		/**
     * 
     */
	 private Integer id; 
		/**
     * 姓名
     */
	 private String name; 
	}