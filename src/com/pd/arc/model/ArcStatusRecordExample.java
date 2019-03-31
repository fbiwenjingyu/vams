package com.pd.arc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.pd.system.framework.PageToExample;

public class ArcStatusRecordExample extends PageToExample{
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    protected List oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public ArcStatusRecordExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    protected ArcStatusRecordExample(ArcStatusRecordExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table ARC_STATUS_RECORD
     *
     * @abatorgenerated Thu Sep 25 10:51:47 CST 2014
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List dateList = new ArrayList();
            Iterator iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(((Date)iter.next()).getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return this;
        }

        public Criteria andIdIn(List values) {
            addCriterion("ID in", values, "id");
            return this;
        }

        public Criteria andIdNotIn(List values) {
            addCriterion("ID not in", values, "id");
            return this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return this;
        }

        public Criteria andXtdabhIsNull() {
            addCriterion("XTDABH is null");
            return this;
        }

        public Criteria andXtdabhIsNotNull() {
            addCriterion("XTDABH is not null");
            return this;
        }

        public Criteria andXtdabhEqualTo(String value) {
            addCriterion("XTDABH =", value, "xtdabh");
            return this;
        }

        public Criteria andXtdabhNotEqualTo(String value) {
            addCriterion("XTDABH <>", value, "xtdabh");
            return this;
        }

        public Criteria andXtdabhGreaterThan(String value) {
            addCriterion("XTDABH >", value, "xtdabh");
            return this;
        }

        public Criteria andXtdabhGreaterThanOrEqualTo(String value) {
            addCriterion("XTDABH >=", value, "xtdabh");
            return this;
        }

        public Criteria andXtdabhLessThan(String value) {
            addCriterion("XTDABH <", value, "xtdabh");
            return this;
        }

        public Criteria andXtdabhLessThanOrEqualTo(String value) {
            addCriterion("XTDABH <=", value, "xtdabh");
            return this;
        }

        public Criteria andXtdabhLike(String value) {
            addCriterion("XTDABH like", value, "xtdabh");
            return this;
        }

        public Criteria andXtdabhNotLike(String value) {
            addCriterion("XTDABH not like", value, "xtdabh");
            return this;
        }

        public Criteria andXtdabhIn(List values) {
            addCriterion("XTDABH in", values, "xtdabh");
            return this;
        }

        public Criteria andXtdabhNotIn(List values) {
            addCriterion("XTDABH not in", values, "xtdabh");
            return this;
        }

        public Criteria andXtdabhBetween(String value1, String value2) {
            addCriterion("XTDABH between", value1, value2, "xtdabh");
            return this;
        }

        public Criteria andXtdabhNotBetween(String value1, String value2) {
            addCriterion("XTDABH not between", value1, value2, "xtdabh");
            return this;
        }

        public Criteria andCzrIsNull() {
            addCriterion("CZR is null");
            return this;
        }

        public Criteria andCzrIsNotNull() {
            addCriterion("CZR is not null");
            return this;
        }

        public Criteria andCzrEqualTo(String value) {
            addCriterion("CZR =", value, "czr");
            return this;
        }

        public Criteria andCzrNotEqualTo(String value) {
            addCriterion("CZR <>", value, "czr");
            return this;
        }

        public Criteria andCzrGreaterThan(String value) {
            addCriterion("CZR >", value, "czr");
            return this;
        }

        public Criteria andCzrGreaterThanOrEqualTo(String value) {
            addCriterion("CZR >=", value, "czr");
            return this;
        }

        public Criteria andCzrLessThan(String value) {
            addCriterion("CZR <", value, "czr");
            return this;
        }

        public Criteria andCzrLessThanOrEqualTo(String value) {
            addCriterion("CZR <=", value, "czr");
            return this;
        }

        public Criteria andCzrLike(String value) {
            addCriterion("CZR like", value, "czr");
            return this;
        }

        public Criteria andCzrNotLike(String value) {
            addCriterion("CZR not like", value, "czr");
            return this;
        }

        public Criteria andCzrIn(List values) {
            addCriterion("CZR in", values, "czr");
            return this;
        }

        public Criteria andCzrNotIn(List values) {
            addCriterion("CZR not in", values, "czr");
            return this;
        }

        public Criteria andCzrBetween(String value1, String value2) {
            addCriterion("CZR between", value1, value2, "czr");
            return this;
        }

        public Criteria andCzrNotBetween(String value1, String value2) {
            addCriterion("CZR not between", value1, value2, "czr");
            return this;
        }

        public Criteria andCzrmcIsNull() {
            addCriterion("CZRMC is null");
            return this;
        }

        public Criteria andCzrmcIsNotNull() {
            addCriterion("CZRMC is not null");
            return this;
        }

        public Criteria andCzrmcEqualTo(String value) {
            addCriterion("CZRMC =", value, "czrmc");
            return this;
        }

        public Criteria andCzrmcNotEqualTo(String value) {
            addCriterion("CZRMC <>", value, "czrmc");
            return this;
        }

        public Criteria andCzrmcGreaterThan(String value) {
            addCriterion("CZRMC >", value, "czrmc");
            return this;
        }

        public Criteria andCzrmcGreaterThanOrEqualTo(String value) {
            addCriterion("CZRMC >=", value, "czrmc");
            return this;
        }

        public Criteria andCzrmcLessThan(String value) {
            addCriterion("CZRMC <", value, "czrmc");
            return this;
        }

        public Criteria andCzrmcLessThanOrEqualTo(String value) {
            addCriterion("CZRMC <=", value, "czrmc");
            return this;
        }

        public Criteria andCzrmcLike(String value) {
            addCriterion("CZRMC like", value, "czrmc");
            return this;
        }

        public Criteria andCzrmcNotLike(String value) {
            addCriterion("CZRMC not like", value, "czrmc");
            return this;
        }

        public Criteria andCzrmcIn(List values) {
            addCriterion("CZRMC in", values, "czrmc");
            return this;
        }

        public Criteria andCzrmcNotIn(List values) {
            addCriterion("CZRMC not in", values, "czrmc");
            return this;
        }

        public Criteria andCzrmcBetween(String value1, String value2) {
            addCriterion("CZRMC between", value1, value2, "czrmc");
            return this;
        }

        public Criteria andCzrmcNotBetween(String value1, String value2) {
            addCriterion("CZRMC not between", value1, value2, "czrmc");
            return this;
        }

        public Criteria andCzsjIsNull() {
            addCriterion("CZSJ is null");
            return this;
        }

        public Criteria andCzsjIsNotNull() {
            addCriterion("CZSJ is not null");
            return this;
        }

        public Criteria andCzsjEqualTo(Date value) {
            addCriterionForJDBCDate("CZSJ =", value, "czsj");
            return this;
        }

        public Criteria andCzsjNotEqualTo(Date value) {
            addCriterionForJDBCDate("CZSJ <>", value, "czsj");
            return this;
        }

        public Criteria andCzsjGreaterThan(Date value) {
            addCriterionForJDBCDate("CZSJ >", value, "czsj");
            return this;
        }

        public Criteria andCzsjGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CZSJ >=", value, "czsj");
            return this;
        }

        public Criteria andCzsjLessThan(Date value) {
            addCriterionForJDBCDate("CZSJ <", value, "czsj");
            return this;
        }

        public Criteria andCzsjLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CZSJ <=", value, "czsj");
            return this;
        }

        public Criteria andCzsjIn(List values) {
            addCriterionForJDBCDate("CZSJ in", values, "czsj");
            return this;
        }

        public Criteria andCzsjNotIn(List values) {
            addCriterionForJDBCDate("CZSJ not in", values, "czsj");
            return this;
        }

        public Criteria andCzsjBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CZSJ between", value1, value2, "czsj");
            return this;
        }

        public Criteria andCzsjNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CZSJ not between", value1, value2, "czsj");
            return this;
        }

        public Criteria andStatusCodeIsNull() {
            addCriterion("STATUS_CODE is null");
            return this;
        }

        public Criteria andStatusCodeIsNotNull() {
            addCriterion("STATUS_CODE is not null");
            return this;
        }

        public Criteria andStatusCodeEqualTo(String value) {
            addCriterion("STATUS_CODE =", value, "statusCode");
            return this;
        }

        public Criteria andStatusCodeNotEqualTo(String value) {
            addCriterion("STATUS_CODE <>", value, "statusCode");
            return this;
        }

        public Criteria andStatusCodeGreaterThan(String value) {
            addCriterion("STATUS_CODE >", value, "statusCode");
            return this;
        }

        public Criteria andStatusCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_CODE >=", value, "statusCode");
            return this;
        }

        public Criteria andStatusCodeLessThan(String value) {
            addCriterion("STATUS_CODE <", value, "statusCode");
            return this;
        }

        public Criteria andStatusCodeLessThanOrEqualTo(String value) {
            addCriterion("STATUS_CODE <=", value, "statusCode");
            return this;
        }

        public Criteria andStatusCodeLike(String value) {
            addCriterion("STATUS_CODE like", value, "statusCode");
            return this;
        }

        public Criteria andStatusCodeNotLike(String value) {
            addCriterion("STATUS_CODE not like", value, "statusCode");
            return this;
        }

        public Criteria andStatusCodeIn(List values) {
            addCriterion("STATUS_CODE in", values, "statusCode");
            return this;
        }

        public Criteria andStatusCodeNotIn(List values) {
            addCriterion("STATUS_CODE not in", values, "statusCode");
            return this;
        }

        public Criteria andStatusCodeBetween(String value1, String value2) {
            addCriterion("STATUS_CODE between", value1, value2, "statusCode");
            return this;
        }

        public Criteria andStatusCodeNotBetween(String value1, String value2) {
            addCriterion("STATUS_CODE not between", value1, value2, "statusCode");
            return this;
        }

        public Criteria andBzIsNull() {
            addCriterion("BZ is null");
            return this;
        }

        public Criteria andBzIsNotNull() {
            addCriterion("BZ is not null");
            return this;
        }

        public Criteria andBzEqualTo(String value) {
            addCriterion("BZ =", value, "bz");
            return this;
        }

        public Criteria andBzNotEqualTo(String value) {
            addCriterion("BZ <>", value, "bz");
            return this;
        }

        public Criteria andBzGreaterThan(String value) {
            addCriterion("BZ >", value, "bz");
            return this;
        }

        public Criteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("BZ >=", value, "bz");
            return this;
        }

        public Criteria andBzLessThan(String value) {
            addCriterion("BZ <", value, "bz");
            return this;
        }

        public Criteria andBzLessThanOrEqualTo(String value) {
            addCriterion("BZ <=", value, "bz");
            return this;
        }

        public Criteria andBzLike(String value) {
            addCriterion("BZ like", value, "bz");
            return this;
        }

        public Criteria andBzNotLike(String value) {
            addCriterion("BZ not like", value, "bz");
            return this;
        }

        public Criteria andBzIn(List values) {
            addCriterion("BZ in", values, "bz");
            return this;
        }

        public Criteria andBzNotIn(List values) {
            addCriterion("BZ not in", values, "bz");
            return this;
        }

        public Criteria andBzBetween(String value1, String value2) {
            addCriterion("BZ between", value1, value2, "bz");
            return this;
        }

        public Criteria andBzNotBetween(String value1, String value2) {
            addCriterion("BZ not between", value1, value2, "bz");
            return this;
        }
    }
}