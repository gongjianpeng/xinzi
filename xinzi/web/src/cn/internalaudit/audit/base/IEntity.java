package cn.internalaudit.audit.base;

public interface IEntity {

	Long getId();

	void setId(Long id);

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	java.util.Date getCreateDate();

	/**
	 *  
	 * @generated
	 * 
	 * 
	 */
	void setCreateDate(java.util.Date value);

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	java.lang.String getCreatePerson();

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	void setCreatePerson(java.lang.String value);

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	java.lang.Long getCreatePersonId();

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	void setCreatePersonId(java.lang.Long value);

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	java.util.Date getLastEditDate();

	/**
	 * 
	 * 
	 * @generated
	 * 
	 * 
	 */
	void setLastEditDate(java.util.Date value);

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	java.lang.String getLastEditPerson();

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	void setLastEditPerson(java.lang.String value);

	/**
	
	 * @generated
	 * 
	 * 
	 */
	java.lang.Long getLastEditPersonId();

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	void setLastEditPersonId(java.lang.Long value);

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	public java.lang.Boolean getIsDelete();

	/**
	 * 
	 * @generated
	 * 
	 * 
	 */
	public void setIsDelete(java.lang.Boolean value);
}
