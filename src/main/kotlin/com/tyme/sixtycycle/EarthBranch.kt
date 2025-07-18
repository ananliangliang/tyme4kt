package com.tyme.sixtycycle

import com.tyme.LoopTyme
import com.tyme.culture.Direction
import com.tyme.culture.Element
import com.tyme.culture.Zodiac
import com.tyme.culture.pengzu.PengZuEarthBranch
import com.tyme.enums.HideHeavenStemType
import com.tyme.enums.YinYang

/**
 * 地支（地元）
 *
 * @author 6tail
 */
class EarthBranch : LoopTyme {
    constructor(index: Int) : super(NAMES, index)

    constructor(name: String) : super(NAMES, name)

    override fun next(n: Int): EarthBranch {
        return EarthBranch(nextIndex(n))
    }

    /**
     * 五行
     *
     * @return 五行
     */
    fun getElement(): Element {
        return Element(intArrayOf(4, 2, 0, 0, 2, 1, 1, 2, 3, 3, 2, 4)[getIndex()])
    }

    /**
     * 阴阳
     *
     * @return 阴阳
     */
    fun getYinYang(): YinYang {
        return if (getIndex() % 2 == 0) YinYang.YANG else YinYang.YIN
    }

    /**
     * 藏干之本气
     *
     * @return 天干
     */
    fun getHideHeavenStemMain(): HeavenStem {
        return HeavenStem(intArrayOf(9, 5, 0, 1, 4, 2, 3, 5, 6, 7, 4, 8)[getIndex()])
    }

    /**
     * 藏干之中气，无中气返回null
     *
     * @return 天干
     */
    fun getHideHeavenStemMiddle(): HeavenStem? {
        val n: Int = intArrayOf(-1, 9, 2, -1, 1, 6, 5, 3, 8, -1, 7, 0)[getIndex()]
        return if (n == -1)  null else HeavenStem(n)
    }

    /**
     * 藏干之余气，无余气返回null
     *
     * @return 天干
     */
    fun getHideHeavenStemResidual(): HeavenStem? {
        val n: Int = intArrayOf(-1, 7, 4, -1, 9, 4, -1, 1, 4, -1, 3, -1)[getIndex()]
        return if (n == -1) null else HeavenStem(n)
    }

    /**
     * 藏干列表
     *
     * @return 藏干列表
     */
    fun getHideHeavenStems(): List<HideHeavenStem>{
        val l: MutableList<HideHeavenStem> = ArrayList()
        l.add(HideHeavenStem(getHideHeavenStemMain(), HideHeavenStemType.MAIN))
        var o: HeavenStem? = getHideHeavenStemMiddle()
        if (null != o) {
            l.add(HideHeavenStem(o, HideHeavenStemType.MIDDLE))
        }
        o = getHideHeavenStemResidual()
        if (null != o) {
            l.add(HideHeavenStem(o, HideHeavenStemType.RESIDUAL))
        }
        return l
    }

    /**
     * 生肖
     *
     * @return 生肖
     */
    fun getZodiac(): Zodiac {
        return Zodiac(getIndex())
    }

    /**
     * 方位
     *
     * @return 方位
     */
    fun getDirection(): Direction {
        return Direction(intArrayOf(0, 4, 2, 2, 4, 8, 8, 4, 6, 6, 4, 0)[getIndex()])
    }

    /**
     * 煞（逢巳日、酉日、丑日必煞东；亥日、卯日、未日必煞西；申日、子日、辰日必煞南；寅日、午日、戌日必煞北。）
     *
     * @return 方位
     */
    fun getOminous(): Direction {
        return Direction(intArrayOf(8, 2, 0, 6)[getIndex() % 4])
    }

    /**
     * 六冲（子午冲，丑未冲，寅申冲，辰戌冲，卯酉冲，巳亥冲）
     *
     * @return 地支
     */
    fun getOpposite(): EarthBranch {
        return next(6)
    }

    /**
     * 六合（子丑合，寅亥合，卯戌合，辰酉合，巳申合，午未合）
     *
     * @return 地支
     */
    fun getCombine(): EarthBranch {
        return EarthBranch(1 - getIndex())
    }

    /**
     * 六害（子未害、丑午害、寅巳害、卯辰害、申亥害、酉戌害）
     *
     * @return 地支
     */
    fun getHarm(): EarthBranch {
        return EarthBranch(19 - getIndex())
    }

    /**
     * 合化（子丑合化土，寅亥合化木，卯戌合化火，辰酉合化金，巳申合化水，午未合化土）
     *
     * @param target 地支
     * @return 五行，如果无法合化，返回null
     */
    fun combine(target: EarthBranch): Element? {
        return if (getCombine() == target) Element(intArrayOf(2, 2, 0, 1, 3, 4, 2, 2, 4, 3, 1, 0)[getIndex()]) else null
    }

    /**
     * 地支彭祖百忌
     *
     * @return 地支彭祖百忌
     */
    fun getPengZuEarthBranch(): PengZuEarthBranch {
        return PengZuEarthBranch(getIndex())
    }

    companion object {
        val NAMES: Array<String> = arrayOf("子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥")

        @JvmStatic
        fun fromIndex(index: Int): EarthBranch {
            return EarthBranch(index)
        }

        @JvmStatic
        fun fromName(name: String): EarthBranch {
            return EarthBranch(name)
        }
    }
}
