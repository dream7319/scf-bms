package com.lierl.api.service.impl;

import com.lierl.api.mapper.OsChinaBlogMapper;
import com.lierl.api.service.IOsChinaBlogService;
import com.lierl.api.spider.bean.OsChinaBlog;
import org.springframework.stereotype.Service;

/**
 * Created by lierl on 2017/9/2.
 */
@Service
public class OsChinaBlogServiceImpl  extends BaseServiceImpl<OsChinaBlogMapper,OsChinaBlog> implements IOsChinaBlogService {
}
