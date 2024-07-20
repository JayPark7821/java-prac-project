package kr.jay.enverspilot.common.entity;

import jakarta.persistence.Entity;
import kr.jay.enverspilot.common.config.MyRevisionEntityListener;
import lombok.ToString;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(MyRevisionEntityListener.class)
@ToString
public class Revision extends DefaultRevisionEntity {

}
