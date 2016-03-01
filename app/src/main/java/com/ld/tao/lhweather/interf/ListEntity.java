package com.ld.tao.lhweather.interf;

import com.ld.tao.lhweather.http.mdel.BaseApiResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tao on 2016/1/28.
 */
public interface ListEntity<T extends BaseApiResponse> extends Serializable {

    public List<T> getList();
}
