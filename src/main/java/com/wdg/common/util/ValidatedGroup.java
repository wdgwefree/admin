package com.wdg.common.util;

import javax.validation.GroupSequence;

/**
 * @description: 校验分组
 * @author: wdg
 * @create: 2023-12-18 14:47
 */
public class ValidatedGroup {

    // 新增使用(配合spring的@Validated功能分组使用)
    public interface Insert {
    }

    // 更新使用(配合spring的@Validated功能分组使用)
    public interface Update {
    }

    // 删除使用(配合spring的@Validated功能分组使用)
    public interface Delete {
    }

    // 属性必须有这三个分组的才验证(配合spring的@Validated功能分组使用)
    @GroupSequence({Insert.class, Update.class, Delete.class})
    public interface All {
    }

}
