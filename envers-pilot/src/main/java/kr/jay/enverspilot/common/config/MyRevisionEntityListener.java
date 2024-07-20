package kr.jay.enverspilot.common.config;

import kr.jay.enverspilot.common.entity.Revision;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.RevisionListener;

@Slf4j
public class MyRevisionEntityListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        Revision entity = (Revision) revisionEntity;
        log.info("Insert Revision {}", entity);
    }
}
