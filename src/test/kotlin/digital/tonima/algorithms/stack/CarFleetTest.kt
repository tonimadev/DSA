package digital.tonima.algorithms.stack

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CarFleetTest {
    private val solution = CarFleetSolution()

    /**
     * Official LeetCode Example 1.
     * Cars: (10,2), (8,4), (0,1), (5,1), (3,3)
     * Times:
     * - Pos 10: (12-10)/2 = 1.0 (Leader) -> Fleet 1
     * - Pos 8:  (12-8)/4 = 1.0  (<= 1.0) -> Merges with 10
     * - Pos 5:  (12-5)/1 = 7.0  (> 1.0) -> New Fleet 2 (Leader)
     * - Pos 3:  (12-3)/3 = 3.0  (<= 7.0) -> Merges with 5
     * - Pos 0:  (12-0)/1 = 12.0 (> 7.0) -> New Fleet 3
     * Result: 3 Fleets
     */
    @Test
    fun testLeetCodeExample1() {
        val target = 12
        val position = intArrayOf(10, 8, 0, 5, 3)
        val speed = intArrayOf(2, 4, 1, 1, 3)
        assertEquals(3, solution.carFleet(target, position, speed))
    }

    /**
     * Official LeetCode Example 2.
     * A single car always results in 1 fleet.
     */
    @Test
    fun testLeetCodeExample2() {
        val target = 10
        val position = intArrayOf(3)
        val speed = intArrayOf(3)
        assertEquals(1, solution.carFleet(target, position, speed))
    }

    /**
     * Official LeetCode Example 3.
     * Cars sorted by position: (4,1), (2,2), (0,4)
     * Times:
     * - Pos 4: (100-4)/1 = 96.0 (Leader)
     * - Pos 2: (100-2)/2 = 49.0 (<= 96) -> Merges
     * - Pos 0: (100-0)/4 = 25.0 (<= 96) -> Merges
     * Result: All merge into the first very slow car. 1 Fleet.
     */
    @Test
    fun testLeetCodeExample3() {
        val target = 100
        val position = intArrayOf(0, 2, 4)
        val speed = intArrayOf(4, 2, 1)
        assertEquals(1, solution.carFleet(target, position, speed))
    }

    /**
     * Edge Case: EXACT collision at the finish line.
     * Pos 10 (Speed 2) -> Time 1.0
     * Pos 8 (Speed 4) -> Time 1.0
     * Since times are equal, they arrive together at the target. LeetCode considers this 1 fleet.
     */
    @Test
    fun testCatchUpExactlyAtTarget() {
        val target = 12
        val position = intArrayOf(10, 8)
        val speed = intArrayOf(2, 4)
        assertEquals(1, solution.carFleet(target, position, speed))
    }

    /**
     * Fast car behind slow car (Far from target).
     * Front: Pos 5, Speed 1 -> Time 5.0
     * Back: Pos 3, Speed 3 -> Time 2.33
     * The one behind is faster and catches up. 1 Fleet.
     */
    @Test
    fun testFastCarBehindSlowCar_Merges() {
        val target = 10
        val position = intArrayOf(3, 5)
        val speed = intArrayOf(3, 1)
        assertEquals(1, solution.carFleet(target, position, speed))
    }

    /**
     * Slow car behind fast car.
     * Front: Pos 5, Speed 5 -> Time 1.0
     * Back: Pos 3, Speed 1 -> Time 7.0
     * The one behind never catches up (7.0 > 1.0). 2 Fleets.
     */
    @Test
    fun testSlowCarBehindFastCar_NoMerge() {
        val target = 10
        val position = intArrayOf(3, 5)
        val speed = intArrayOf(1, 5)
        assertEquals(2, solution.carFleet(target, position, speed))
    }

    /**
     * Large Scale Test (Simple Stress Test).
     * No merging: All cars behind are slower than those in front.
     * Pos: 4, 3, 2, 1, 0
     * Speed: 5, 4, 3, 2, 1
     * Times:
     * - 4 (Speed 5, Dist 6) -> 1.2
     * - 3 (Speed 4, Dist 7) -> 1.75 (> 1.2, New)
     * - 2 (Speed 3, Dist 8) -> 2.66 (> 1.75, New)
     * ...
     * Result: 5 Fleets.
     */
    @Test
    fun testDescendingSpeed_NoMerges() {
        val target = 10
        val position = intArrayOf(0, 1, 2, 3, 4) // Reverse sorted becomes 4,3,2,1,0
        val speed = intArrayOf(1, 2, 3, 4, 5)    // Associated speeds
        assertEquals(5, solution.carFleet(target, position, speed))
    }
}
