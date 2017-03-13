package me.sonam.dev.corelibs.pagination;


import me.sonam.dev.corelibs.pagination.core.PaginationStrategy;

/**
 * 分页策略工厂，通过{@link #getStrategy(StrategyFactory)}来获取具体的策略。
 */
public enum StrategyFactory {

    PageStrategy("me.sonam.dev.corelibs.pagination.strategy.PageStrategy"),
    ListResultStrategy("me.sonam.dev.corelibs.pagination.strategy.ListResultStrategy");

    String value;

    public String getValue() {
        return value;
    }

    StrategyFactory(String value) {
        this.value = value;
    }

    /**
     * 获取具体的策略
     */
    public static PaginationStrategy getStrategy(StrategyFactory strategy) {
        PaginationStrategy result = null;
        try {
            result = (PaginationStrategy) Class.forName(strategy.getValue()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
