package me.sonam.dev.fastandroiddemo;

import java.util.List;

import me.sonam.dev.corelibs.entity.BaseRows;

/**
 * Created by SonamLee on 2017/3/7.
 */
public class LoginEntity extends BaseRows{
    private String createBy;

    private long createtime;

    private String updatetime;

    private String updateBy;

    private String updateuser;

    private String createuser;

    private String version;

    private String owner;

    private long spaceid;

    private String userPos;

    private long userOrgId;

    private long userId;

    private String userAreaId;

    private String userAreaName;

    private String userAreaPath;

    private long personId;

    private String fullname;

    private String account;

    private String password;

    private long isExpired;

    private long isLock;

    private int status;

    private String email;

    private String mobile;

    private String phone;

    private int sex;

    private String picture;

    private String retype;

    private String report;

    private int fromType;

    private String posAlias;

    private String channelid;

    private String def1;

    private String def4;

    private String companyid;

    private int djparty;

    private String djpartyname;

    private String orgName;

    private List<Authorities> authorities ;

    private String ip;

    private boolean accountNonExpired;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;

    private boolean enabled;

    private String roles;

    private String username;

    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }
    public String getCreateBy(){
        return this.createBy;
    }
    public void setCreatetime(long createtime){
        this.createtime = createtime;
    }
    public long getCreatetime(){
        return this.createtime;
    }
    public void setUpdatetime(String updatetime){
        this.updatetime = updatetime;
    }
    public String getUpdatetime(){
        return this.updatetime;
    }
    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }
    public String getUpdateBy(){
        return this.updateBy;
    }
    public void setUpdateuser(String updateuser){
        this.updateuser = updateuser;
    }
    public String getUpdateuser(){
        return this.updateuser;
    }
    public void setCreateuser(String createuser){
        this.createuser = createuser;
    }
    public String getCreateuser(){
        return this.createuser;
    }
    public void setVersion(String version){
        this.version = version;
    }
    public String getVersion(){
        return this.version;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }
    public String getOwner(){
        return this.owner;
    }
    public void setSpaceid(long spaceid){
        this.spaceid = spaceid;
    }
    public long getSpaceid(){
        return this.spaceid;
    }
    public void setUserPos(String userPos){
        this.userPos = userPos;
    }
    public String getUserPos(){
        return this.userPos;
    }
    public void setUserOrgId(long userOrgId){
        this.userOrgId = userOrgId;
    }
    public long getUserOrgId(){
        return this.userOrgId;
    }
    public void setUserId(long userId){
        this.userId = userId;
    }
    public long getUserId(){
        return this.userId;
    }
    public void setUserAreaId(String userAreaId){
        this.userAreaId = userAreaId;
    }
    public String getUserAreaId(){
        return this.userAreaId;
    }
    public void setUserAreaName(String userAreaName){
        this.userAreaName = userAreaName;
    }
    public String getUserAreaName(){
        return this.userAreaName;
    }
    public void setUserAreaPath(String userAreaPath){
        this.userAreaPath = userAreaPath;
    }
    public String getUserAreaPath(){
        return this.userAreaPath;
    }
    public void setPersonId(long personId){
        this.personId = personId;
    }
    public long getPersonId(){
        return this.personId;
    }
    public void setFullname(String fullname){
        this.fullname = fullname;
    }
    public String getFullname(){
        return this.fullname;
    }
    public void setAccount(String account){
        this.account = account;
    }
    public String getAccount(){
        return this.account;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setIsExpired(long isExpired){
        this.isExpired = isExpired;
    }
    public long getIsExpired(){
        return this.isExpired;
    }
    public void setIsLock(long isLock){
        this.isLock = isLock;
    }
    public long getIsLock(){
        return this.isLock;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){
        return this.mobile;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setSex(int sex){
        this.sex = sex;
    }
    public int getSex(){
        return this.sex;
    }
    public void setPicture(String picture){
        this.picture = picture;
    }
    public String getPicture(){
        return this.picture;
    }
    public void setRetype(String retype){
        this.retype = retype;
    }
    public String getRetype(){
        return this.retype;
    }
    public void setReport(String report){
        this.report = report;
    }
    public String getReport(){
        return this.report;
    }
    public void setFromType(int fromType){
        this.fromType = fromType;
    }
    public int getFromType(){
        return this.fromType;
    }
    public void setPosAlias(String posAlias){
        this.posAlias = posAlias;
    }
    public String getPosAlias(){
        return this.posAlias;
    }
    public void setChannelid(String channelid){
        this.channelid = channelid;
    }
    public String getChannelid(){
        return this.channelid;
    }
    public void setDef1(String def1){
        this.def1 = def1;
    }
    public String getDef1(){
        return this.def1;
    }
    public void setDef4(String def4){
        this.def4 = def4;
    }
    public String getDef4(){
        return this.def4;
    }
    public void setCompanyid(String companyid){
        this.companyid = companyid;
    }
    public String getCompanyid(){
        return this.companyid;
    }
    public void setDjparty(int djparty){
        this.djparty = djparty;
    }
    public int getDjparty(){
        return this.djparty;
    }
    public void setDjpartyname(String djpartyname){
        this.djpartyname = djpartyname;
    }
    public String getDjpartyname(){
        return this.djpartyname;
    }
    public void setOrgName(String orgName){
        this.orgName = orgName;
    }
    public String getOrgName(){
        return this.orgName;
    }
    public void setAuthorities(List<Authorities> authorities){
        this.authorities = authorities;
    }
    public List<Authorities> getAuthorities(){
        return this.authorities;
    }
    public void setIp(String ip){
        this.ip = ip;
    }
    public String getIp(){
        return this.ip;
    }
    public void setAccountNonExpired(boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }
    public boolean getAccountNonExpired(){
        return this.accountNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public boolean getCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    public void setAccountNonLocked(boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }
    public boolean getAccountNonLocked(){
        return this.accountNonLocked;
    }
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    public boolean getEnabled(){
        return this.enabled;
    }
    public void setRoles(String roles){
        this.roles = roles;
    }
    public String getRoles(){
        return this.roles;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

    public class Authorities {
        private String authority;

        public void setAuthority(String authority){
            this.authority = authority;
        }
        public String getAuthority(){
            return this.authority;
        }

    }
}
