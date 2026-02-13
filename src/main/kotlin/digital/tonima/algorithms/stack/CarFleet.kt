package digital.tonima.algorithms.stack

/**
 * LeetCode Problem 853: Car Fleet
 * https://leetcode.com/problems/car-fleet/
 *
 * Time Complexity: O(n log n) - dominated by sorting the cars by position
 * Space Complexity: O(n) - for storing the paired and sorted cars array
 *
 * Approach:
 * - Sort cars by position in descending order (closest to target first)
 * - Calculate time to reach target for each car
 * - If a car takes longer than the previous fleet's time, it forms a new fleet
 * - Otherwise, it catches up and joins the existing fleet
 */
class CarFleetSolution {
    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        // If there are no cars, return 0 fleets
        if (position.isEmpty()) return 0

        // 1. Zip and Sort
        // Primary Criterion: Descending Position (closest to target first)
        // Tiebreaker Criterion: Ascending Speed (if positions are equal, process slower car first)
        // This ensures that if cars start at the same position, the slower one sets the fleet pace immediately.
        val cars = Array(position.size) { i ->
            Pair(position[i], speed[i])
        }
        cars.sortByDescending { it.first }

        var fleets = 0
        var currentFleetTime = 0.0

        for ((position, speed) in cars) {
            // Calculate precise time to reach target
            val time = (target - position).toDouble() / speed

            // If this car's time is STRICTLY GREATER than the front fleet's time,
            // it cannot catch up and becomes the leader of a new fleet.
            if (time > currentFleetTime) {
                fleets++
                currentFleetTime = time
            }
            // Otherwise (time <= currentFleetTime), it arrives earlier or at the same time,
            // so it catches up to the front fleet and merges with it.
        }

        return fleets
    }
}
