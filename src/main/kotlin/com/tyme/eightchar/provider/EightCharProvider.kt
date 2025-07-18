package com.tyme.eightchar.provider

import com.tyme.eightchar.EightChar
import com.tyme.lunar.LunarHour

/**
 * 八字计算接口
 *
 * @author 6tail
 */
interface EightCharProvider {
    /**
     * 八字
     *
     * @param hour 农历时辰
     * @return 八字
     */
    fun getEightChar(hour: LunarHour): EightChar
}
