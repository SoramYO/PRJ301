/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonnx.registration;

import java.io.Serializable;

/**
 *
 * @author Son
 */
public class RegitrationCreateError implements Serializable{
    private String usernameLenghtError;
    private String passwordLenghtError;
    private String fullNameLenghtError;
    private String userNameIsExisted;
    private String confirmNotMatched;

    public RegitrationCreateError() {
    }

    /**
     * @return the usernameLenghtError
     */
    public String getUsernameLenghtError() {
        return usernameLenghtError;
    }

    /**
     * @param usernameLenghtError the usernameLenghtError to set
     */
    public void setUsernameLenghtError(String usernameLenghtError) {
        this.usernameLenghtError = usernameLenghtError;
    }

    /**
     * @return the passwordLenghtError
     */
    public String getPasswordLenghtError() {
        return passwordLenghtError;
    }

    /**
     * @param passwordLenghtError the passwordLenghtError to set
     */
    public void setPasswordLenghtError(String passwordLenghtError) {
        this.passwordLenghtError = passwordLenghtError;
    }

    /**
     * @return the fullNameLenghtError
     */
    public String getFullNameLenghtError() {
        return fullNameLenghtError;
    }

    /**
     * @param fullNameLenghtError the fullNameLenghtError to set
     */
    public void setFullNameLenghtError(String fullNameLenghtError) {
        this.fullNameLenghtError = fullNameLenghtError;
    }

    /**
     * @return the userNameIsExisted
     */
    public String getUserNameIsExisted() {
        return userNameIsExisted;
    }

    /**
     * @param userNameIsExisted the userNameIsExisted to set
     */
    public void setUserNameIsExisted(String userNameIsExisted) {
        this.userNameIsExisted = userNameIsExisted;
    }

    /**
     * @return the confirmNotMatched
     */
    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    /**
     * @param confirmNotMatched the confirmNotMatched to set
     */
    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }
    
}
