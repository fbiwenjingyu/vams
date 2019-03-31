package com.pd.right.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pd.system.framework.PageToExample;

public class DeptExample  extends PageToExample{
    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    protected String orderByClause;

    /**
     * This field was generated by Abator for iBATIS.
     * This field corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    protected List oredCriteria;

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    public DeptExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    protected DeptExample(DeptExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
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
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table DEPT
     *
     * @abatorgenerated Tue Jun 25 10:04:54 CST 2013
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

        public Criteria andDeptcodeIsNull() {
            addCriterion("DEPTCODE is null");
            return this;
        }

        public Criteria andDeptcodeIsNotNull() {
            addCriterion("DEPTCODE is not null");
            return this;
        }

        public Criteria andDeptcodeEqualTo(String value) {
            addCriterion("DEPTCODE =", value, "deptcode");
            return this;
        }

        public Criteria andDeptcodeNotEqualTo(String value) {
            addCriterion("DEPTCODE <>", value, "deptcode");
            return this;
        }

        public Criteria andDeptcodeGreaterThan(String value) {
            addCriterion("DEPTCODE >", value, "deptcode");
            return this;
        }

        public Criteria andDeptcodeGreaterThanOrEqualTo(String value) {
            addCriterion("DEPTCODE >=", value, "deptcode");
            return this;
        }

        public Criteria andDeptcodeLessThan(String value) {
            addCriterion("DEPTCODE <", value, "deptcode");
            return this;
        }

        public Criteria andDeptcodeLessThanOrEqualTo(String value) {
            addCriterion("DEPTCODE <=", value, "deptcode");
            return this;
        }

        public Criteria andDeptcodeLike(String value) {
            addCriterion("DEPTCODE like", value, "deptcode");
            return this;
        }

        public Criteria andDeptcodeNotLike(String value) {
            addCriterion("DEPTCODE not like", value, "deptcode");
            return this;
        }

        public Criteria andDeptcodeIn(List values) {
            addCriterion("DEPTCODE in", values, "deptcode");
            return this;
        }

        public Criteria andDeptcodeNotIn(List values) {
            addCriterion("DEPTCODE not in", values, "deptcode");
            return this;
        }

        public Criteria andDeptcodeBetween(String value1, String value2) {
            addCriterion("DEPTCODE between", value1, value2, "deptcode");
            return this;
        }

        public Criteria andDeptcodeNotBetween(String value1, String value2) {
            addCriterion("DEPTCODE not between", value1, value2, "deptcode");
            return this;
        }

        public Criteria andDepttypeIsNull() {
            addCriterion("DEPTTYPE is null");
            return this;
        }

        public Criteria andDepttypeIsNotNull() {
            addCriterion("DEPTTYPE is not null");
            return this;
        }

        public Criteria andDepttypeEqualTo(Long value) {
            addCriterion("DEPTTYPE =", value, "depttype");
            return this;
        }

        public Criteria andDepttypeNotEqualTo(Long value) {
            addCriterion("DEPTTYPE <>", value, "depttype");
            return this;
        }

        public Criteria andDepttypeGreaterThan(Long value) {
            addCriterion("DEPTTYPE >", value, "depttype");
            return this;
        }

        public Criteria andDepttypeGreaterThanOrEqualTo(Long value) {
            addCriterion("DEPTTYPE >=", value, "depttype");
            return this;
        }

        public Criteria andDepttypeLessThan(Long value) {
            addCriterion("DEPTTYPE <", value, "depttype");
            return this;
        }

        public Criteria andDepttypeLessThanOrEqualTo(Long value) {
            addCriterion("DEPTTYPE <=", value, "depttype");
            return this;
        }

        public Criteria andDepttypeIn(List values) {
            addCriterion("DEPTTYPE in", values, "depttype");
            return this;
        }

        public Criteria andDepttypeNotIn(List values) {
            addCriterion("DEPTTYPE not in", values, "depttype");
            return this;
        }

        public Criteria andDepttypeBetween(Long value1, Long value2) {
            addCriterion("DEPTTYPE between", value1, value2, "depttype");
            return this;
        }

        public Criteria andDepttypeNotBetween(Long value1, Long value2) {
            addCriterion("DEPTTYPE not between", value1, value2, "depttype");
            return this;
        }

        public Criteria andDeptnameIsNull() {
            addCriterion("DEPTNAME is null");
            return this;
        }

        public Criteria andDeptnameIsNotNull() {
            addCriterion("DEPTNAME is not null");
            return this;
        }

        public Criteria andDeptnameEqualTo(String value) {
            addCriterion("DEPTNAME =", value, "deptname");
            return this;
        }

        public Criteria andDeptnameNotEqualTo(String value) {
            addCriterion("DEPTNAME <>", value, "deptname");
            return this;
        }

        public Criteria andDeptnameGreaterThan(String value) {
            addCriterion("DEPTNAME >", value, "deptname");
            return this;
        }

        public Criteria andDeptnameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPTNAME >=", value, "deptname");
            return this;
        }

        public Criteria andDeptnameLessThan(String value) {
            addCriterion("DEPTNAME <", value, "deptname");
            return this;
        }

        public Criteria andDeptnameLessThanOrEqualTo(String value) {
            addCriterion("DEPTNAME <=", value, "deptname");
            return this;
        }

        public Criteria andDeptnameLike(String value) {
            addCriterion("DEPTNAME like", value, "deptname");
            return this;
        }

        public Criteria andDeptnameNotLike(String value) {
            addCriterion("DEPTNAME not like", value, "deptname");
            return this;
        }

        public Criteria andDeptnameIn(List values) {
            addCriterion("DEPTNAME in", values, "deptname");
            return this;
        }

        public Criteria andDeptnameNotIn(List values) {
            addCriterion("DEPTNAME not in", values, "deptname");
            return this;
        }

        public Criteria andDeptnameBetween(String value1, String value2) {
            addCriterion("DEPTNAME between", value1, value2, "deptname");
            return this;
        }

        public Criteria andDeptnameNotBetween(String value1, String value2) {
            addCriterion("DEPTNAME not between", value1, value2, "deptname");
            return this;
        }

        public Criteria andUpcodeIsNull() {
            addCriterion("UPCODE is null");
            return this;
        }

        public Criteria andUpcodeIsNotNull() {
            addCriterion("UPCODE is not null");
            return this;
        }

        public Criteria andUpcodeEqualTo(String value) {
            addCriterion("UPCODE =", value, "upcode");
            return this;
        }

        public Criteria andUpcodeNotEqualTo(String value) {
            addCriterion("UPCODE <>", value, "upcode");
            return this;
        }

        public Criteria andUpcodeGreaterThan(String value) {
            addCriterion("UPCODE >", value, "upcode");
            return this;
        }

        public Criteria andUpcodeGreaterThanOrEqualTo(String value) {
            addCriterion("UPCODE >=", value, "upcode");
            return this;
        }

        public Criteria andUpcodeLessThan(String value) {
            addCriterion("UPCODE <", value, "upcode");
            return this;
        }

        public Criteria andUpcodeLessThanOrEqualTo(String value) {
            addCriterion("UPCODE <=", value, "upcode");
            return this;
        }

        public Criteria andUpcodeLike(String value) {
            addCriterion("UPCODE like", value, "upcode");
            return this;
        }

        public Criteria andUpcodeNotLike(String value) {
            addCriterion("UPCODE not like", value, "upcode");
            return this;
        }

        public Criteria andUpcodeIn(List values) {
            addCriterion("UPCODE in", values, "upcode");
            return this;
        }

        public Criteria andUpcodeNotIn(List values) {
            addCriterion("UPCODE not in", values, "upcode");
            return this;
        }

        public Criteria andUpcodeBetween(String value1, String value2) {
            addCriterion("UPCODE between", value1, value2, "upcode");
            return this;
        }

        public Criteria andUpcodeNotBetween(String value1, String value2) {
            addCriterion("UPCODE not between", value1, value2, "upcode");
            return this;
        }

        public Criteria andDeptdescIsNull() {
            addCriterion("DEPTDESC is null");
            return this;
        }

        public Criteria andDeptdescIsNotNull() {
            addCriterion("DEPTDESC is not null");
            return this;
        }

        public Criteria andDeptdescEqualTo(String value) {
            addCriterion("DEPTDESC =", value, "deptdesc");
            return this;
        }

        public Criteria andDeptdescNotEqualTo(String value) {
            addCriterion("DEPTDESC <>", value, "deptdesc");
            return this;
        }

        public Criteria andDeptdescGreaterThan(String value) {
            addCriterion("DEPTDESC >", value, "deptdesc");
            return this;
        }

        public Criteria andDeptdescGreaterThanOrEqualTo(String value) {
            addCriterion("DEPTDESC >=", value, "deptdesc");
            return this;
        }

        public Criteria andDeptdescLessThan(String value) {
            addCriterion("DEPTDESC <", value, "deptdesc");
            return this;
        }

        public Criteria andDeptdescLessThanOrEqualTo(String value) {
            addCriterion("DEPTDESC <=", value, "deptdesc");
            return this;
        }

        public Criteria andDeptdescLike(String value) {
            addCriterion("DEPTDESC like", value, "deptdesc");
            return this;
        }

        public Criteria andDeptdescNotLike(String value) {
            addCriterion("DEPTDESC not like", value, "deptdesc");
            return this;
        }

        public Criteria andDeptdescIn(List values) {
            addCriterion("DEPTDESC in", values, "deptdesc");
            return this;
        }

        public Criteria andDeptdescNotIn(List values) {
            addCriterion("DEPTDESC not in", values, "deptdesc");
            return this;
        }

        public Criteria andDeptdescBetween(String value1, String value2) {
            addCriterion("DEPTDESC between", value1, value2, "deptdesc");
            return this;
        }

        public Criteria andDeptdescNotBetween(String value1, String value2) {
            addCriterion("DEPTDESC not between", value1, value2, "deptdesc");
            return this;
        }

        public Criteria andLinkaddIsNull() {
            addCriterion("LINKADD is null");
            return this;
        }

        public Criteria andLinkaddIsNotNull() {
            addCriterion("LINKADD is not null");
            return this;
        }

        public Criteria andLinkaddEqualTo(String value) {
            addCriterion("LINKADD =", value, "linkadd");
            return this;
        }

        public Criteria andLinkaddNotEqualTo(String value) {
            addCriterion("LINKADD <>", value, "linkadd");
            return this;
        }

        public Criteria andLinkaddGreaterThan(String value) {
            addCriterion("LINKADD >", value, "linkadd");
            return this;
        }

        public Criteria andLinkaddGreaterThanOrEqualTo(String value) {
            addCriterion("LINKADD >=", value, "linkadd");
            return this;
        }

        public Criteria andLinkaddLessThan(String value) {
            addCriterion("LINKADD <", value, "linkadd");
            return this;
        }

        public Criteria andLinkaddLessThanOrEqualTo(String value) {
            addCriterion("LINKADD <=", value, "linkadd");
            return this;
        }

        public Criteria andLinkaddLike(String value) {
            addCriterion("LINKADD like", value, "linkadd");
            return this;
        }

        public Criteria andLinkaddNotLike(String value) {
            addCriterion("LINKADD not like", value, "linkadd");
            return this;
        }

        public Criteria andLinkaddIn(List values) {
            addCriterion("LINKADD in", values, "linkadd");
            return this;
        }

        public Criteria andLinkaddNotIn(List values) {
            addCriterion("LINKADD not in", values, "linkadd");
            return this;
        }

        public Criteria andLinkaddBetween(String value1, String value2) {
            addCriterion("LINKADD between", value1, value2, "linkadd");
            return this;
        }

        public Criteria andLinkaddNotBetween(String value1, String value2) {
            addCriterion("LINKADD not between", value1, value2, "linkadd");
            return this;
        }

        public Criteria andLinktelIsNull() {
            addCriterion("LINKTEL is null");
            return this;
        }

        public Criteria andLinktelIsNotNull() {
            addCriterion("LINKTEL is not null");
            return this;
        }

        public Criteria andLinktelEqualTo(String value) {
            addCriterion("LINKTEL =", value, "linktel");
            return this;
        }

        public Criteria andLinktelNotEqualTo(String value) {
            addCriterion("LINKTEL <>", value, "linktel");
            return this;
        }

        public Criteria andLinktelGreaterThan(String value) {
            addCriterion("LINKTEL >", value, "linktel");
            return this;
        }

        public Criteria andLinktelGreaterThanOrEqualTo(String value) {
            addCriterion("LINKTEL >=", value, "linktel");
            return this;
        }

        public Criteria andLinktelLessThan(String value) {
            addCriterion("LINKTEL <", value, "linktel");
            return this;
        }

        public Criteria andLinktelLessThanOrEqualTo(String value) {
            addCriterion("LINKTEL <=", value, "linktel");
            return this;
        }

        public Criteria andLinktelLike(String value) {
            addCriterion("LINKTEL like", value, "linktel");
            return this;
        }

        public Criteria andLinktelNotLike(String value) {
            addCriterion("LINKTEL not like", value, "linktel");
            return this;
        }

        public Criteria andLinktelIn(List values) {
            addCriterion("LINKTEL in", values, "linktel");
            return this;
        }

        public Criteria andLinktelNotIn(List values) {
            addCriterion("LINKTEL not in", values, "linktel");
            return this;
        }

        public Criteria andLinktelBetween(String value1, String value2) {
            addCriterion("LINKTEL between", value1, value2, "linktel");
            return this;
        }

        public Criteria andLinktelNotBetween(String value1, String value2) {
            addCriterion("LINKTEL not between", value1, value2, "linktel");
            return this;
        }

        public Criteria andLinkmailIsNull() {
            addCriterion("LINKMAIL is null");
            return this;
        }

        public Criteria andLinkmailIsNotNull() {
            addCriterion("LINKMAIL is not null");
            return this;
        }

        public Criteria andLinkmailEqualTo(String value) {
            addCriterion("LINKMAIL =", value, "linkmail");
            return this;
        }

        public Criteria andLinkmailNotEqualTo(String value) {
            addCriterion("LINKMAIL <>", value, "linkmail");
            return this;
        }

        public Criteria andLinkmailGreaterThan(String value) {
            addCriterion("LINKMAIL >", value, "linkmail");
            return this;
        }

        public Criteria andLinkmailGreaterThanOrEqualTo(String value) {
            addCriterion("LINKMAIL >=", value, "linkmail");
            return this;
        }

        public Criteria andLinkmailLessThan(String value) {
            addCriterion("LINKMAIL <", value, "linkmail");
            return this;
        }

        public Criteria andLinkmailLessThanOrEqualTo(String value) {
            addCriterion("LINKMAIL <=", value, "linkmail");
            return this;
        }

        public Criteria andLinkmailLike(String value) {
            addCriterion("LINKMAIL like", value, "linkmail");
            return this;
        }

        public Criteria andLinkmailNotLike(String value) {
            addCriterion("LINKMAIL not like", value, "linkmail");
            return this;
        }

        public Criteria andLinkmailIn(List values) {
            addCriterion("LINKMAIL in", values, "linkmail");
            return this;
        }

        public Criteria andLinkmailNotIn(List values) {
            addCriterion("LINKMAIL not in", values, "linkmail");
            return this;
        }

        public Criteria andLinkmailBetween(String value1, String value2) {
            addCriterion("LINKMAIL between", value1, value2, "linkmail");
            return this;
        }

        public Criteria andLinkmailNotBetween(String value1, String value2) {
            addCriterion("LINKMAIL not between", value1, value2, "linkmail");
            return this;
        }

        public Criteria andLinkwebIsNull() {
            addCriterion("LINKWEB is null");
            return this;
        }

        public Criteria andLinkwebIsNotNull() {
            addCriterion("LINKWEB is not null");
            return this;
        }

        public Criteria andLinkwebEqualTo(String value) {
            addCriterion("LINKWEB =", value, "linkweb");
            return this;
        }

        public Criteria andLinkwebNotEqualTo(String value) {
            addCriterion("LINKWEB <>", value, "linkweb");
            return this;
        }

        public Criteria andLinkwebGreaterThan(String value) {
            addCriterion("LINKWEB >", value, "linkweb");
            return this;
        }

        public Criteria andLinkwebGreaterThanOrEqualTo(String value) {
            addCriterion("LINKWEB >=", value, "linkweb");
            return this;
        }

        public Criteria andLinkwebLessThan(String value) {
            addCriterion("LINKWEB <", value, "linkweb");
            return this;
        }

        public Criteria andLinkwebLessThanOrEqualTo(String value) {
            addCriterion("LINKWEB <=", value, "linkweb");
            return this;
        }

        public Criteria andLinkwebLike(String value) {
            addCriterion("LINKWEB like", value, "linkweb");
            return this;
        }

        public Criteria andLinkwebNotLike(String value) {
            addCriterion("LINKWEB not like", value, "linkweb");
            return this;
        }

        public Criteria andLinkwebIn(List values) {
            addCriterion("LINKWEB in", values, "linkweb");
            return this;
        }

        public Criteria andLinkwebNotIn(List values) {
            addCriterion("LINKWEB not in", values, "linkweb");
            return this;
        }

        public Criteria andLinkwebBetween(String value1, String value2) {
            addCriterion("LINKWEB between", value1, value2, "linkweb");
            return this;
        }

        public Criteria andLinkwebNotBetween(String value1, String value2) {
            addCriterion("LINKWEB not between", value1, value2, "linkweb");
            return this;
        }

        public Criteria andF1IsNull() {
            addCriterion("F1 is null");
            return this;
        }

        public Criteria andF1IsNotNull() {
            addCriterion("F1 is not null");
            return this;
        }

        public Criteria andF1EqualTo(String value) {
            addCriterion("F1 =", value, "f1");
            return this;
        }

        public Criteria andF1NotEqualTo(String value) {
            addCriterion("F1 <>", value, "f1");
            return this;
        }

        public Criteria andF1GreaterThan(String value) {
            addCriterion("F1 >", value, "f1");
            return this;
        }

        public Criteria andF1GreaterThanOrEqualTo(String value) {
            addCriterion("F1 >=", value, "f1");
            return this;
        }

        public Criteria andF1LessThan(String value) {
            addCriterion("F1 <", value, "f1");
            return this;
        }

        public Criteria andF1LessThanOrEqualTo(String value) {
            addCriterion("F1 <=", value, "f1");
            return this;
        }

        public Criteria andF1Like(String value) {
            addCriterion("F1 like", value, "f1");
            return this;
        }

        public Criteria andF1NotLike(String value) {
            addCriterion("F1 not like", value, "f1");
            return this;
        }

        public Criteria andF1In(List values) {
            addCriterion("F1 in", values, "f1");
            return this;
        }

        public Criteria andF1NotIn(List values) {
            addCriterion("F1 not in", values, "f1");
            return this;
        }

        public Criteria andF1Between(String value1, String value2) {
            addCriterion("F1 between", value1, value2, "f1");
            return this;
        }

        public Criteria andF1NotBetween(String value1, String value2) {
            addCriterion("F1 not between", value1, value2, "f1");
            return this;
        }

        public Criteria andF2IsNull() {
            addCriterion("F2 is null");
            return this;
        }

        public Criteria andF2IsNotNull() {
            addCriterion("F2 is not null");
            return this;
        }

        public Criteria andF2EqualTo(String value) {
            addCriterion("F2 =", value, "f2");
            return this;
        }

        public Criteria andF2NotEqualTo(String value) {
            addCriterion("F2 <>", value, "f2");
            return this;
        }

        public Criteria andF2GreaterThan(String value) {
            addCriterion("F2 >", value, "f2");
            return this;
        }

        public Criteria andF2GreaterThanOrEqualTo(String value) {
            addCriterion("F2 >=", value, "f2");
            return this;
        }

        public Criteria andF2LessThan(String value) {
            addCriterion("F2 <", value, "f2");
            return this;
        }

        public Criteria andF2LessThanOrEqualTo(String value) {
            addCriterion("F2 <=", value, "f2");
            return this;
        }

        public Criteria andF2Like(String value) {
            addCriterion("F2 like", value, "f2");
            return this;
        }

        public Criteria andF2NotLike(String value) {
            addCriterion("F2 not like", value, "f2");
            return this;
        }

        public Criteria andF2In(List values) {
            addCriterion("F2 in", values, "f2");
            return this;
        }

        public Criteria andF2NotIn(List values) {
            addCriterion("F2 not in", values, "f2");
            return this;
        }

        public Criteria andF2Between(String value1, String value2) {
            addCriterion("F2 between", value1, value2, "f2");
            return this;
        }

        public Criteria andF2NotBetween(String value1, String value2) {
            addCriterion("F2 not between", value1, value2, "f2");
            return this;
        }

        public Criteria andF3IsNull() {
            addCriterion("F3 is null");
            return this;
        }

        public Criteria andF3IsNotNull() {
            addCriterion("F3 is not null");
            return this;
        }

        public Criteria andF3EqualTo(String value) {
            addCriterion("F3 =", value, "f3");
            return this;
        }

        public Criteria andF3NotEqualTo(String value) {
            addCriterion("F3 <>", value, "f3");
            return this;
        }

        public Criteria andF3GreaterThan(String value) {
            addCriterion("F3 >", value, "f3");
            return this;
        }

        public Criteria andF3GreaterThanOrEqualTo(String value) {
            addCriterion("F3 >=", value, "f3");
            return this;
        }

        public Criteria andF3LessThan(String value) {
            addCriterion("F3 <", value, "f3");
            return this;
        }

        public Criteria andF3LessThanOrEqualTo(String value) {
            addCriterion("F3 <=", value, "f3");
            return this;
        }

        public Criteria andF3Like(String value) {
            addCriterion("F3 like", value, "f3");
            return this;
        }

        public Criteria andF3NotLike(String value) {
            addCriterion("F3 not like", value, "f3");
            return this;
        }

        public Criteria andF3In(List values) {
            addCriterion("F3 in", values, "f3");
            return this;
        }

        public Criteria andF3NotIn(List values) {
            addCriterion("F3 not in", values, "f3");
            return this;
        }

        public Criteria andF3Between(String value1, String value2) {
            addCriterion("F3 between", value1, value2, "f3");
            return this;
        }

        public Criteria andF3NotBetween(String value1, String value2) {
            addCriterion("F3 not between", value1, value2, "f3");
            return this;
        }

        public Criteria andF4IsNull() {
            addCriterion("F4 is null");
            return this;
        }

        public Criteria andF4IsNotNull() {
            addCriterion("F4 is not null");
            return this;
        }

        public Criteria andF4EqualTo(String value) {
            addCriterion("F4 =", value, "f4");
            return this;
        }

        public Criteria andF4NotEqualTo(String value) {
            addCriterion("F4 <>", value, "f4");
            return this;
        }

        public Criteria andF4GreaterThan(String value) {
            addCriterion("F4 >", value, "f4");
            return this;
        }

        public Criteria andF4GreaterThanOrEqualTo(String value) {
            addCriterion("F4 >=", value, "f4");
            return this;
        }

        public Criteria andF4LessThan(String value) {
            addCriterion("F4 <", value, "f4");
            return this;
        }

        public Criteria andF4LessThanOrEqualTo(String value) {
            addCriterion("F4 <=", value, "f4");
            return this;
        }

        public Criteria andF4Like(String value) {
            addCriterion("F4 like", value, "f4");
            return this;
        }

        public Criteria andF4NotLike(String value) {
            addCriterion("F4 not like", value, "f4");
            return this;
        }

        public Criteria andF4In(List values) {
            addCriterion("F4 in", values, "f4");
            return this;
        }

        public Criteria andF4NotIn(List values) {
            addCriterion("F4 not in", values, "f4");
            return this;
        }

        public Criteria andF4Between(String value1, String value2) {
            addCriterion("F4 between", value1, value2, "f4");
            return this;
        }

        public Criteria andF4NotBetween(String value1, String value2) {
            addCriterion("F4 not between", value1, value2, "f4");
            return this;
        }

        public Criteria andF5IsNull() {
            addCriterion("F5 is null");
            return this;
        }

        public Criteria andF5IsNotNull() {
            addCriterion("F5 is not null");
            return this;
        }

        public Criteria andF5EqualTo(String value) {
            addCriterion("F5 =", value, "f5");
            return this;
        }

        public Criteria andF5NotEqualTo(String value) {
            addCriterion("F5 <>", value, "f5");
            return this;
        }

        public Criteria andF5GreaterThan(String value) {
            addCriterion("F5 >", value, "f5");
            return this;
        }

        public Criteria andF5GreaterThanOrEqualTo(String value) {
            addCriterion("F5 >=", value, "f5");
            return this;
        }

        public Criteria andF5LessThan(String value) {
            addCriterion("F5 <", value, "f5");
            return this;
        }

        public Criteria andF5LessThanOrEqualTo(String value) {
            addCriterion("F5 <=", value, "f5");
            return this;
        }

        public Criteria andF5Like(String value) {
            addCriterion("F5 like", value, "f5");
            return this;
        }

        public Criteria andF5NotLike(String value) {
            addCriterion("F5 not like", value, "f5");
            return this;
        }

        public Criteria andF5In(List values) {
            addCriterion("F5 in", values, "f5");
            return this;
        }

        public Criteria andF5NotIn(List values) {
            addCriterion("F5 not in", values, "f5");
            return this;
        }

        public Criteria andF5Between(String value1, String value2) {
            addCriterion("F5 between", value1, value2, "f5");
            return this;
        }

        public Criteria andF5NotBetween(String value1, String value2) {
            addCriterion("F5 not between", value1, value2, "f5");
            return this;
        }

        public Criteria andDeptareaIsNull() {
            addCriterion("DEPTAREA is null");
            return this;
        }

        public Criteria andDeptareaIsNotNull() {
            addCriterion("DEPTAREA is not null");
            return this;
        }

        public Criteria andDeptareaEqualTo(String value) {
            addCriterion("DEPTAREA =", value, "deptarea");
            return this;
        }

        public Criteria andDeptareaNotEqualTo(String value) {
            addCriterion("DEPTAREA <>", value, "deptarea");
            return this;
        }

        public Criteria andDeptareaGreaterThan(String value) {
            addCriterion("DEPTAREA >", value, "deptarea");
            return this;
        }

        public Criteria andDeptareaGreaterThanOrEqualTo(String value) {
            addCriterion("DEPTAREA >=", value, "deptarea");
            return this;
        }

        public Criteria andDeptareaLessThan(String value) {
            addCriterion("DEPTAREA <", value, "deptarea");
            return this;
        }

        public Criteria andDeptareaLessThanOrEqualTo(String value) {
            addCriterion("DEPTAREA <=", value, "deptarea");
            return this;
        }

        public Criteria andDeptareaLike(String value) {
            addCriterion("DEPTAREA like", value, "deptarea");
            return this;
        }

        public Criteria andDeptareaNotLike(String value) {
            addCriterion("DEPTAREA not like", value, "deptarea");
            return this;
        }

        public Criteria andDeptareaIn(List values) {
            addCriterion("DEPTAREA in", values, "deptarea");
            return this;
        }

        public Criteria andDeptareaNotIn(List values) {
            addCriterion("DEPTAREA not in", values, "deptarea");
            return this;
        }

        public Criteria andDeptareaBetween(String value1, String value2) {
            addCriterion("DEPTAREA between", value1, value2, "deptarea");
            return this;
        }

        public Criteria andDeptareaNotBetween(String value1, String value2) {
            addCriterion("DEPTAREA not between", value1, value2, "deptarea");
            return this;
        }
    }
}