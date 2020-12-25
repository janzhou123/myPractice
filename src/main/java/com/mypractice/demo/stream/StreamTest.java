package com.mypractice.demo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: com.mypractice.demo.stream
 * @Author: zhouweiyi
 * @Date: 2020/12/25 8:29 AM
 */
public class StreamTest {

  public static void main(String[] args) {
    List<Person> personList = new ArrayList<Person>();
    personList.add(new Person("Tom", 8900, 20, "male", "New York"));
    personList.add(new Person("Jack", 7000, 34, "male", "Washington"));
    personList.add(new Person("Lily", 7800, 50, "female", "Washington"));
    personList.add(new Person("Anni", 8200, 60, "female", "New York"));
    personList.add(new Person("Owen", 9500, 40, "male", "New York"));
    personList.add(new Person("Alisa", 7900, 47, "female", "New York"));

    List<String> filterList = personList.stream().filter(x -> x.getSalary() > 8000)
        .map(Person::getName).collect(Collectors.toList());
    System.out.println("高于8000的员工姓名：" + filterList);
    //============================================================================

    List<Integer> list = Arrays.asList(7, 6, 8, 3, 9, 2, 1);
    list.stream().filter(x -> x > 6).forEach(System.out::println);

    Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
    Optional<Integer> findAny = list.stream().filter(x -> x > 6).findAny();

    boolean anyMatch = list.stream().anyMatch(x -> x < 6);
    System.out.println("匹配第一个值：" + findFirst.get());
    System.out.println("匹配任意一个值：" + findAny.get());
    System.out.println("是否存在大于6的值：" + anyMatch);
    //============================================================================

    /**
     * 获取String集合中最长的元素
     */
    System.out.println("===============[获取String集合中最长的元素]=========================");
    List<String> maxlist = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
    Optional<String> max = maxlist.stream().max(Comparator.comparing(String::length));
    System.out.println("最长的字符串：" + max.get());

    /**
     * 获取Integer集合中的最大值
     */
    System.out.println("===============[获取Integer集合中的最大值]=========================");
    List<Integer> intlist = Arrays.asList(7, 6, 9, 4, 11, 6);

    // 自然排序
    Optional<Integer> max1 = intlist.stream().max(Integer::compareTo);
    // 自定义排序
    Optional<Integer> max2 = intlist.stream().max(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
      }
    });
    System.out.println("自然排序的最大值：" + max1.get());
    System.out.println("自定义排序的最大值：" + max2.get());

    /**
     * 获取员工工资最高的人
     */
    System.out.println("===============[获取员工工资最高的人]=========================");
    Optional<Person> max4 = personList.stream().max(Comparator.comparingInt(Person::getSalary));
    System.out.println("员工工资最大值：" + max4.get().getSalary());

    /**
     * 计算Integer集合中大于6的元素的个数
     */
    System.out.println("===============[计算Integer集合中大于6的元素的个数]=========================");
    List<Integer> list3 = Arrays.asList(7, 6, 4, 8, 2, 11, 9);

    long count = list3.stream().filter(x -> x > 6).count();
    System.out.println("list中大于6的元素个数：" + count);
    /**
     * 英文字符串数组的元素全部改为大写。整数数组每个元素+3
     */
    System.out.println("===============[英文字符串数组的元素全部改为大写。整数数组每个元素+3]=========================");
    String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
    List<String> strList = Arrays.stream(strArr).map(String::toUpperCase)
        .collect(Collectors.toList());
    List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
    List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());

    System.out.println("每个元素大写：" + strList);
    System.out.println("每个元素+3：" + intListNew);
    /**
     * 将员工的薪资全部增加1000
     */
    System.out.println("===============[将员工的薪资全部增加1000]=========================");
    // 不改变原来员工集合的方式
    List<Person> personListNew = personList.stream().map(person -> {
      Person personNew = new Person(person.getName(), 0, 0, null, null);
      personNew.setSalary(person.getSalary() + 10000);
      return personNew;
    }).collect(Collectors.toList());
    System.out
        .println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
    System.out.println(
        "一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());
    List<Person> personListNew2 = personList.stream().map(person -> {
      person.setSalary(person.getSalary() + 10000);
      return person;
    }).collect(Collectors.toList());
    System.out
        .println("二次改动前：" + personList.get(0).getName() + "-->" + personListNew.get(0).getSalary());
    System.out.println(
        "二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew.get(0).getSalary());
/**
 * 将两个字符数组合并成一个新的字符数组
 */

    System.out.println("===============[将两个字符数组合并成一个新的字符数组]=========================");
    List<String> list5 = Arrays.asList("m,k,l,a", "1,3,5,7");
    List<String> listNew = list5.stream().flatMap(s -> {
      // 将每个元素转换成一个stream
      String[] split = s.split(",");
      Stream<String> s2 = Arrays.stream(split);
      return s2;
    }).collect(Collectors.toList());
    System.out.println("处理前的集合：" + list5);
    System.out.println("处理后的集合：" + listNew);

    /**
     * 归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
     */
    System.out.println("===============[求Integer集合的元素之和、乘积和最大值]=========================");
    List<Integer> list6 = Arrays.asList(1, 3, 2, 8, 11, 4);
    //求和
    Optional<Integer> sum1 = list6.stream().reduce((x, y) -> x + y);
    Optional<Integer> sum2 = list6.stream().reduce(Integer::sum);
    Integer sum3 = list6.stream().reduce(0, Integer::sum);
    //求乘积
    Optional<Integer> product = list6.stream().reduce((x, y) -> x * y);
    //求最大值
    Optional<Integer> max3 = list6.stream().reduce((x, y) -> x > y ? x : y);
    Integer max6 = list6.stream().reduce(1, Integer::max);
    System.out.println("list求和：" + sum1.get() + "," + sum2.get() + "," + sum3);
    System.out.println("list求积：" + product.get());
    System.out.println("list求最大值：" + max3.get() + "," + max6);

    /**
     * 求所有员工的工资之和和最高工资
     */
    System.out.println("===============[求所有员工的工资之和和最高工资]=========================");
// 求工资之和方式1：
    Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
    // 求工资之和方式2：
    Integer sumSalary2 = personList.stream().reduce(0, (sum, p) -> sum += p.getSalary(),
        (sum11, sum22) -> sum11 + sum22);
    // 求工资之和方式3：
    Integer sumSalary3 = personList.stream()
        .reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);

    // 求最高工资方式1：
    Integer maxSalary = personList.stream()
        .reduce(0, (max11, p) -> max11 > p.getSalary() ? max11 : p.getSalary(),
            Integer::max);
    // 求最高工资方式2：
    Integer maxSalary2 = personList.stream()
        .reduce(0, (max22, p) -> max22 > p.getSalary() ? max22 : p.getSalary(),
            (max13, max23) -> max13 > max23 ? max13 : max23);

    System.out.println("工资之和：" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3);
    System.out.println("最高工资：" + maxSalary + "," + maxSalary2);
    /**
     * collect，收集，可以说是内容最繁多、功能最丰富的部分了。从字面上去理解，就是把一个流收集起来，最终可以是收集成一个值也可以收集成一个新的集合。
     */
    System.out.println("===============[案例演示toList、toSet和toMap]=========================");
    List<Integer> list12 = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
    List<Integer> listNew12 = list12.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
    Set<Integer> set = list12.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

    Map<?, Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
        .collect(Collectors.toMap(Person::getName, p -> p));
    System.out.println("toList:" + listNew12);
    System.out.println("toSet:" + set);
    System.out.println("toMap:" + map);
    /**
     * 统计员工人数、平均工资、工资总额、最高工资
     */
    System.out.println("===============[统计员工人数、平均工资、工资总额、最高工资]=========================");
// 求总数
    Long count2 = personList.stream().collect(Collectors.counting());
    // 求平均工资
    Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
    // 求最高工资
    Optional<Integer> max222 = personList.stream().map(Person::getSalary)
        .collect(Collectors.maxBy(Integer::compare));
    // 求工资之和
    Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
    // 一次性统计所有信息
    DoubleSummaryStatistics collect = personList.stream()
        .collect(Collectors.summarizingDouble(Person::getSalary));

    System.out.println("员工总数：" + count2);
    System.out.println("员工平均工资：" + average);
    System.out.println("员工工资总和：" + sum);
    System.out.println("员工工资所有统计：" + collect);
    System.out.println("员工求最高工资：" + max222);
    /**
     * 将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组
     */
    System.out.println("===============[将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组]=========================");
    // 将员工按薪资是否高于8000分组
    Map<Boolean, List<Person>> part = personList.stream()
        .collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
    // 将员工按性别分组
    Map<String, List<Person>> group = personList.stream()
        .collect(Collectors.groupingBy(Person::getSex));
    // 将员工先按性别分组，再按地区分组
    Map<String, Map<String, List<Person>>> group2 = personList.stream()
        .collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
    System.out.println("员工按薪资是否大于8000分组情况：" + part);
    System.out.println("员工按性别分组情况：" + group);
    System.out.println("员工按性别、地区：" + group2);
    /**
     * 接合(joining)
     *
     * joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
     */
    System.out.println(
        "===============[joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串]=========================");
    String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
    System.out.println("所有员工的姓名：" + names);
    List<String> list123 = Arrays.asList("A", "B", "C");
    String string = list123.stream().collect(Collectors.joining("-"));
    System.out.println("拼接后的字符串：" + string);
    /**
     * 归约(reducing)
     *
     * Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
     */
    System.out.println(
        "===============[Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持]=========================");
    // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
    sum = personList.stream()
        .collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
    System.out.println("员工扣税薪资总和：" + sum);

    // stream的reduce
    Optional<Integer> sum23 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
    System.out.println("员工薪资总和：" + sum23.get());
    /**
     * 排序(sorted)
     *
     * sorted，中间操作。有两种排序：
     * sorted()：自然排序，流中元素需实现Comparable接口
     * sorted(Comparator com)：Comparator排序器自定义排序
     */

    System.out.println(
        "===============[将员工按工资由高到低（工资一样则按年龄由大到小）排序]=========================");
    // 按工资增序排序
    List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
        .collect(Collectors.toList());
    // 按工资倒序排序
    List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
        .map(Person::getName).collect(Collectors.toList());
    // 先按工资再按年龄自然排序（从小到大）
    List<String> newList3 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
        .map(Person::getName).collect(Collectors.toList());
    // 先按工资再按年龄自定义排序（从大到小）
    List<String> newList4 = personList.stream().sorted((p1, p2) -> {
      if (p1.getSalary() == p2.getSalary()) {
        return p2.getAge() - p1.getAge();
      } else {
        return p2.getSalary() - p1.getSalary();
      }
    }).map(Person::getName).collect(Collectors.toList());

    System.out.println("按工资自然排序：" + newList);
    System.out.println("按工资降序排序：" + newList2);
    System.out.println("先按工资再按年龄自然排序：" + newList3);
    System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    /**
     * 提取/组合
     *
     * 流也可以进行合并、去重、限制、跳过等操作。
     */
    System.out.println(
        "===============[流也可以进行合并、去重、限制、跳过等操作]=========================");
    String[] arr1 = { "a", "b", "c", "d" };
    String[] arr2 = { "d", "e", "f", "g" };

    Stream<String> stream1 = Stream.of(arr1);
    Stream<String> stream2 = Stream.of(arr2);
    // concat:合并两个流 distinct：去重
    List<String> newListA = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
    // limit：限制从流中获得前n个数据
    List<Integer> collectA = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
    // skip：跳过前n个数据
    List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());

    System.out.println("流合并：" + newListA);
    System.out.println("limit：" + collectA);
    System.out.println("skip：" + collect2);
  }

}
