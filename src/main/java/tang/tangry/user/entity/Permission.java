package tang.tangry.user.entity;

import java.util.List;

public class Permission {
    private Integer permissionId;

    private String permissionOperation;

    private String permissionDescription;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionOperation() {
        return permissionOperation;
    }

    public void setPermissionOperation(String permissionOperation) {
        this.permissionOperation = permissionOperation == null ? null : permissionOperation.trim();
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription == null ? null : permissionDescription.trim();
    }
}