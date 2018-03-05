package com.util;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 加载多个包（进行别名处理）
 *
 * @author pgs
 * @version 1.0
 */
public class PackagesSqlSessionFactoryBean extends SqlSessionFactoryBean {

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private static final Logger LOGGER = LoggerFactory.getLogger(PackagesSqlSessionFactoryBean.class);

    @Override
    public void setTypeAliasesPackage(String typeAliasesPackage) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
        String[] typeAliasesPackageAaary = typeAliasesPackage.trim().split(",");
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (String typeAliasesPackageStr : typeAliasesPackageAaary) {
            String typeAliasesPackageRes = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                    ClassUtils.convertClassNameToResourcePath(typeAliasesPackageStr) + "/" + DEFAULT_RESOURCE_PATTERN;
            //将加载多个绝对匹配的所有Resource
            //将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分
            //然后进行遍历模式匹配
            try {
                List<String> result = new ArrayList<>();
                Resource[] resources = resolver.getResources(typeAliasesPackageRes);
                if (resources != null && resources.length > 0) {
                    MetadataReader metadataReader;
                    for (Resource resource : resources) {
                        if (resource.isReadable()) {
                            metadataReader = metadataReaderFactory.getMetadataReader(resource);
                            try {
                                result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (result.size() > 0) {
                    if (count > 0 && !sb.toString().endsWith(",")) {
                        sb.append(",");
                    }
                    for (int i = 0; i < result.size(); i++) {
                        count++;
                        sb.append(result.get(i));
                        if (i < result.size() - 1) {
                            sb.append(",");
                        }
                    }
                } else {
                    LOGGER.info("参数typeAliasesPackage:{}，未找到任何包！", typeAliasesPackage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (count > 0) {
            super.setTypeAliasesPackage(sb.toString());
        }
    }
}