package me.sonam.dev.corelibs.pagination.presenter;

import me.sonam.dev.corelibs.base.BasePaginationView;
import me.sonam.dev.corelibs.pagination.StrategyFactory;

/**
 * 此类使用{@link me.sonam.dev.corelibs.pagination.strategy.ListResultStrategy}分页。
 * 使用{@link #getPageNo()}与{@link #getPageSize()}获取接口需要的分页参数。
 * 调用{@link #doPagination(boolean)}并根据其返回值判断是否能继续获取下一页。
 * 需要传入List类型的集合的分页条件。
 */
public abstract class ListPagePresenter<T extends BasePaginationView> extends PagePresenter<T> {

    public ListPagePresenter() {
        setPaginationStrategy(StrategyFactory.getStrategy(StrategyFactory.ListResultStrategy));
    }
}
