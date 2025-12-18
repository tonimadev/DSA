package digital.tonima.dataestructures

fun main() {
    val linkedList = LinkedList<Int>()

    // === APPEND ===
    println("=== APPEND ===")
    linkedList.append(20)
    linkedList.append(100)
    linkedList.append(200)
    linkedList.printAll()

    // === REMOVE LAST ===
    println("\n=== REMOVE LAST ===")
    println("Removed: ${linkedList.removeLast()}")
    linkedList.printAll()

    // === PREPEND ===
    println("\n=== PREPEND ===")
    linkedList.prepend(1)
    linkedList.prepend(2)
    linkedList.printAll()

    // === REMOVE FIRST ===
    println("\n=== REMOVE FIRST ===")
    println("Removed: ${linkedList.removeFirst()}")
    linkedList.printAll()

    // === GET ===
    println("\n=== GET ===")
    println("Get index -1: ${linkedList.get(-1)}")
    println("Get index 0: ${linkedList.get(0)}")
    println("Get index 1: ${linkedList.get(1)}")
    println("Get index 2: ${linkedList.get(2)}")
    println("Get index 3: ${linkedList.get(3)}")

    // === SET ===
    println("\n=== SET ===")
    println("Set index 10 to 99: ${linkedList.set(10, 99)}")
    println("Set index 0 to 50: ${linkedList.set(0, 50)}")
    linkedList.printAll()

    // === INSERT ===
    println("\n=== INSERT ===")
    println("Insert 999 at index 1: ${linkedList.insert(1, 999)}")
    linkedList.printAll()

    println("Insert 888 at index 0: ${linkedList.insert(0, 888)}")
    linkedList.printAll()

    println("Insert 777 at end: ${linkedList.insert(linkedList.size, 777)}")
    linkedList.printAll()

    // === REMOVE ===
    println("\n=== REMOVE ===")
    println("Remove at index -1: ${linkedList.remove(-1)}")
    println("Remove at index 100: ${linkedList.remove(100)}")
    linkedList.printAll()

    println("Remove at index 2: ${linkedList.remove(2)}")
    linkedList.printAll()

    println("Remove at index 0 (first): ${linkedList.remove(0)}")
    linkedList.printAll()

    println("Remove at last index: ${linkedList.remove(linkedList.size - 1)}")
    linkedList.printAll()

    // === REVERSE ===
    println("\n=== REVERSE ===")
    println("Before reverse:")
    linkedList.printList()
    linkedList.getHead()
    linkedList.getTail()

    linkedList.reverse()

    println("\nAfter reverse:")
    linkedList.printList()
    linkedList.getHead()
    linkedList.getTail()
}

