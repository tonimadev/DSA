package digital.tonima.dataestructures.array

fun main() {
    val unsortedArray = UnsortedArray<Int>(4)

    unsortedArray.insert(10)
    unsortedArray.insert(20)
    unsortedArray.insert(30)
    unsortedArray.insert(40)
    unsortedArray.remove(3)
    unsortedArray.printIt()
}
