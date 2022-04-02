package la.foton.msf.auth.enums;

/**
 * Enum de Roles de segurança.
 *
 * @author Vitor Sulzbach
 */
public enum RolesEnum {
    USER("ROLE_USER"),
    TI("ROLE_TI"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    RolesEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
