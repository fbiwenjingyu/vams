package com.pd.quartz;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 外检查验定时任务
 * @author wangwei
 * 2013-12-19
 */
public class CleraCallQuartz {
	
	private static JdbcTemplate jdbcTemplate;
	
	public static void clearCallNumber(){
        String sqlDeleteNum = "delete from ARC_CALL";
        jdbcTemplate.execute(sqlDeleteNum);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		CleraCallQuartz.jdbcTemplate = jdbcTemplate;
	}
	
}
