package cn.neu.demo

object ForDemo {
  def main(args: Array[String]): Unit = {

    // 1、多个生成器的使用（相当与嵌套循环）
    for (i <- 1 to 3; j <- 1 to 3) print(f"${10 * i + j}%3d")
    // Output: 11 12 13 21 22 23 31 32 33

    // 2、生成器守卫条件的使用
    for (i <- 1 to 3; j <- 1 to 3 if i != j) print(f"${10 * i + j}%3d")
    // Output: 12 13  21 23 31 32

    // 3、在循环中引入变量
    for (i <- 1 to 3; from = 4 - i; j <- from to 3) print(f"${10 * i + j}%3d")
    // Output: 13 22 23 31 32 33

    // 4、yield会用每次迭代生成的结果构造集合,这就是for推导式
    print(for (i <- 1 to 10) yield i % 3)
    // Output: Vector(1, 2, 0, 1, 2, 0, 1, 2, 0, 1)

    // 和第一个生成器类型兼容的
    print(for (c <- "Hello"; i <- 0 to 1) yield (c + i).toChar)
    // Output: HIeflmlmop
    print(for (i <- 0 to 1; c <- "Hello") yield (c + i).toChar)
    // Output: Vector(H, e, l, l, o, I, f, m, m, p)

  }
}
