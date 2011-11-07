package at.irian.jsfatwork.domain;

abstract class AbstractBaseEntity implements BaseEntity {

    public boolean isTransient() {
        return getId() == null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BaseEntity))
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (this.getId() == null || other.getId() == null) {
            return false;
        }
        return this.getId() != null && this.getId().equals(other.getId());
    }

    public int hashCode() {
        return this.getId() != null ? this.getId().hashCode() : super.hashCode();
    }

}
