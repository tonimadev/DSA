package digital.tonima.algorithms.arrays

/**
 * Base class that simulates the Version Control API from LeetCode #278
 * This is the fake API provided by LeetCode for testing purposes.
 *
 * In the actual LeetCode environment, this class would be pre-defined
 * and you would only implement the firstBadVersion() function in the subclass.
 */
abstract class VersionControl {

    /**
     * The first bad version number.
     * This is set internally for testing purposes.
     */
    protected var firstBad: Int = 0

    /**
     * Forward declaration of isBadVersion API.
     * @param version The version number to check
     * @return true if the version is bad, false otherwise
     */
    fun isBadVersion(version: Int): Boolean {
        return version >= firstBad
    }

    /**
     * Helper method to set the first bad version for testing.
     * This would not exist in the actual LeetCode environment.
     */
    fun setFirstBadVersion(version: Int) {
        firstBad = version
    }

    /**
     * Abstract method to find the first bad version.
     * @param n Total number of versions
     * @return The first bad version number
     */
    abstract fun firstBadVersion(n: Int): Int
}

/* The isBadVersion API is defined in the parent class VersionControl.
      fun isBadVersion(version: Int) : Boolean {} */

/**
 * LeetCode #278 - First Bad Version
 * https://leetcode.com/problems/first-bad-version/
 *
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions
 * after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * Time Complexity: O(log n) - Binary search halves the search space in each iteration
 * Space Complexity: O(1) - Only uses a constant amount of extra space
 */
class FirstBadVersionSolution : VersionControl() {
    override fun firstBadVersion(n: Int): Int {
        var start = 1
        var end = n

        while (start < end) {
            val mid = start + (end - start) / 2
            if (isBadVersion(mid)) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        return start
    }
}
