package tang.tangry.user.entity;

import java.util.List;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String realName;

    private String mobile;

    private String headImage;
    //加了shiro，需要额外的role表
    private List<Role> roleList;

    public List<Role> getRoles() {
        return roleList;
    }

    public void setRoles(List<Role> rolesList) {
        this.roleList = rolesList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }
}