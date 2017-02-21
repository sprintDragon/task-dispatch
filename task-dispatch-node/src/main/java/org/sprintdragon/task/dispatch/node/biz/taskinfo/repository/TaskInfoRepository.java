package org.sprintdragon.task.dispatch.node.biz.taskinfo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.sprintdragon.task.dispatch.node.biz.taskinfo.domain.TaskInfoPo;

/**
 * Created by wangdi on 16-11-3.
 */
@Repository("taskInfoRepository")
public interface TaskInfoRepository extends PagingAndSortingRepository<TaskInfoPo, Long> {

    Page<TaskInfoPo> findByExecuteTimeBetween(Long begin, Long end, Pageable pageable);

}
