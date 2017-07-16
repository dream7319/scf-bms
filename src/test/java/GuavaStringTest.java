import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.common.primitives.Ints;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Nullable;
import java.io.*;
import java.net.URL;
import java.util.*;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by Administrator on 2017/6/28.
 */
public class GuavaStringTest {
    /**
     * Guava字符串处理：分割，连接，匹配，格式
     */
    @Test
    public void joiner(){
        Joiner joiner = Joiner.on(";").skipNulls();//跳过null进行链接
        String result = joiner.join("a",null,"e","d",null);
        System.out.println(result);

        List<Integer> integers = Ints.asList(1, 2, 3, 4);
        String ints = joiner.join(integers);
        System.out.println(ints);
    }

    /**
     * 测试拆分器-Splitter
     * .trimResults()去掉每个结果的左右空格
     * .omitEmptyStrings() 去掉结果为空的字符串
     */
    @Test
    public void splitter(){
        String str = "aa,bb,,   cc  ,dd,,";
        List<String> list = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
        for (String ss:list) {
            System.out.println("--"+ss);
        }

        String str1 = "aabbccdd";
        //每个字符串长度最长为2
        List<String> strings = Splitter.fixedLength(2).splitToList(str1);
        strings.forEach(s->{
            System.out.println(s);
        });
    }

    /**
     * 测试字符匹配器
     */
    @Test
    public void charMacher(){
        //只保留数字字符
        String theDigits = CharMatcher.digit().retainFrom("今天是2016年9月16日");
        CharMatcher.digit().removeFrom("some text 89983 and more");//删除数字

        System.out.println(theDigits);
        Assert.assertEquals(theDigits,"2016916");

        //去除两端的空格，并把中间的连续空格替换成单个空格
        String spaced = CharMatcher.whitespace().trimAndCollapseFrom("  一个 CharMatcher   代表 一类 字符 ", ' ');
        System.out.println(spaced);
        Assert.assertEquals(spaced,"一个 CharMatcher 代表 一类 字符");
        //用*号替换所有数字
        String noDigits = CharMatcher.javaDigit().replaceFrom("我的手机号是13400001234", "*");
        System.out.println(noDigits);
        Assert.assertEquals(noDigits,"我的手机号是***********");

        // 只保留数字和小写字母
        String lowerAndDigit = CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom("yutianran1314@gmail.COM");
        System.out.println(lowerAndDigit);
        Assert.assertEquals(lowerAndDigit,"yutianran1314gmail");

        Ints.join(";",new int[]{1,2,3,4});
    }

    /**
     * 格式化
     */
    @Test
    public void caseFormat(){
        String lower_camel = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "helloGuava");
        System.out.println(lower_camel);

        String lower_hypen = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "hello-guava");
        System.out.println(lower_hypen);
    }

    @Test
    public void preconditions(){
        int count = 1;

        Preconditions.checkArgument(count>1,"must be %s",count);

        Preconditions.checkNotNull("");
    }

    @Test
    public void byteStream() throws IOException {
        InputStream input = new FileInputStream("");
        OutputStream output = new FileOutputStream("");
        ByteStreams.copy(input,output);

        Reader reader = new InputStreamReader(new FileInputStream(""));
        Writer writer = new FileWriter("");
        CharStreams.copy(reader,writer);

        File f1 = new File("");
        File f2 = new File("");
        Files.copy(f1,f2);
        Files.copy(f1,output);

        Files.move(f1,f2);

        URL url = Resources.getResource("config.xml"); //获取classpath根下的config.xml文件url
    }

    @Test
    public void collections(){
        Map<String,List<String>> maps = newHashMap();
        Lists.newArrayList();
        Sets.newHashSet();
        ImmutableList<String> strs = ImmutableList.of("a", "b", "c");
        Lists.newArrayList(strs);

        ImmutableMap<String,String> map = ImmutableMap.of("key1", "value1", "key2", "value2");
        newHashMap(map);

        Ints.compare(1,2);
    }


    @Test
    public void functions(){
        HashMap<String, Integer> map1 = Maps.newHashMap();
        Maps.transformValues(map1,new Function<Integer, Integer>() {
            public Integer apply(Integer in) {
              return in + 1;
            }
        });

        ArrayList<String> lists = Lists.newArrayList("Aleksander", "Jaran", "Integrasco", "Guava", "Java");

        Iterable<String> datas = Iterables.filter(lists, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String input) {
                return input.length()>5;
            }
        });//去除长度大于5的数据

        Iterables.filter(lists,Predicates.containsPattern("a"));

        Iterables.filter(lists,Predicates.or(Predicates.or(Predicates.equalTo("Jaran"),
                Predicates.equalTo("Guava")),Predicates.equalTo("Aleksander")));

        Collections2.filter(lists,Predicates.containsPattern("a"));

        List<Integer> integers = Lists.transform(lists, new Function<String, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable String input) {
                return Integer.valueOf(input.trim());
            }
        });

        Collection<Object> objects = Collections2.transform(lists, new Function<String, Object>() {
            @Nullable
            @Override
            public Object apply(@Nullable String input) {
                return null;
            }
        });

        Collections2.transform(lists,Functions.forPredicate(Predicates.containsPattern("a")));

        Map<Integer, Object> map3 = Maps.newHashMap();

        Function<String, Integer> functions = Functions.forMap(map1);
        Function<Integer, Object> stateFunction = Functions.forMap(map3);
        //可以对不同的函数进行组合,将FunctionB的输出作为FunctionA的输入进行再处理。可以实现嵌套的数据处理操作。
        //Predicates.compose 将Predicate和Function进行组合。将Function的结果作为Predicate的输入，然后进行判断过滤操作
        Function<String, Object> compose = Functions.compose(stateFunction, functions);

        ImmutableList<String> strings = FluentIterable.from(lists).filter(Predicates.containsPattern("a")).filter(Predicates.notNull())
                .toList();

        Predicates.isNull();

        Predicates.notNull();

    }

    @Test
    public void collection(){
        ImmutableList<String> strs = ImmutableList.of("a", "b", "c");
        ArrayList<String> lists = Lists.newArrayList(strs);

        //根据last name排序，再根据first name排序，然后对排序结果反序
//        Comparator<Person> byLastName = new Comparator<Person>() {
//            public int compare(final Person p1, final Person p2) {
//                return p1.getLastName().compareTo(p2.getLastName());
//            }
//        };
//
//        Comparator<Person> byFirstName = new Comparator<Person>() {
//            public int compare(final Person p1, final Person p2) {
//                return p1.getFirstName().compareTo(p2.getFirstName());
//            }
//        };
//        List<Person> sortedCopy = Ordering.from(byLastName).compound(byFirstName).reverse().sortedCopy(persons);

        //natural()：使用Comparable类型的自然顺序， 例如：整数从小到大，字符串是按字典顺序;
        //usingToString() ：使用toString()返回的字符串按字典顺序进行排序；
        //arbitrary() ：返回一个所有对象的任意顺序， 即compare(a, b) == 0 就是 a == b (identity equality)。 本身的排序是没有任何含义， 但是在VM的生命周期是一个常量
        Ordering<String> naturalOrdering = Ordering.natural();
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();
        /**
         * 操作方法：
         　　reverse(): 返回与当前Ordering相反的排序:
         　　nullsFirst(): 返回一个将null放在non-null元素之前的Ordering，其他的和原始的Ordering一样；
         　　nullsLast()：返回一个将null放在non-null元素之后的Ordering，其他的和原始的Ordering一样；
         　　compound(Comparator)：返回一个使用Comparator的Ordering，Comparator作为第二排序元素，例如对bug列表进行排序，先根据bug的级别，再根据优先级进行排序；
         　　lexicographical()：返回一个按照字典元素迭代的Ordering；
         　　onResultOf(Function)：将function应用在各个元素上之后, 在使用原始ordering进行排序；
         　　greatestOf(Iterable iterable, int k)：返回指定的第k个可迭代的最大的元素，按照这个从最大到最小的顺序。是不稳定的。
         　　leastOf(Iterable<E> iterable,int k)：返回指定的第k个可迭代的最小的元素，按照这个从最小到最大的顺序。是不稳定的。
         　　isOrdered(Iterable)：是否有序，Iterable不能少于2个元素。
         　　isStrictlyOrdered(Iterable)：是否严格有序。请注意，Iterable不能少于两个元素。
         　　sortedCopy(Iterable)：返回指定的元素作为一个列表的排序副本。
         */
    }

    @Test
    public void multimap(){
        HashSet setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet setB = Sets.newHashSet(4, 5, 6, 7, 8);

        Sets.SetView union = Sets.union(setA, setB);
        Sets.difference(setA,setB);

        Sets.intersection(setA,setB);//交集

        Map<String,Object> map1 = newHashMap();
        Map<String,Object> map2 = newHashMap();

        MapDifference<String, Object> mapDifference = Maps.difference(map1, map2);
        mapDifference.areEqual();
        mapDifference.entriesDiffering();
        mapDifference.entriesOnlyOnLeft();
        mapDifference.entriesOnlyOnRight();
        mapDifference.entriesInCommon();

        /**
         * Google Collections提供了多种Multimaps的实现，如果你想防止出现键值对，可以用HashMultimap；
         * 如果你需要键值对按照自然顺序排列，你可以使用TreeMultimap；甚至你想按插入顺序来遍历集合，
         * LinkedHashMultimap可以满足你的需求。
         */

        /**
         * Multimap也支持一系列强大的视图功能：
         　　1.asMap把自身Multimap<K, V>映射成Map<K, Collection<V>>视图。
            这个Map视图支持remove和修改操作，但是不支持put和putAll。严格地来讲，当你希望传入参数是不存在的key，
            而且你希望返回的是null而不是一个空的可修改的集合的时候就可以调用asMap().get(key)。
            (你可以强制转型asMap().get(key)的结果类型－对SetMultimap的结果转成Set，对ListMultimap的结果转成List型－但是直接把ListMultimap转成Map<K, List<V>>是不行的。）
         　　2.entries视图是把Multimap里所有的键值对以Collection<Map.Entry<K, V>>的形式展现。
         　　3.keySet视图是把Multimap的键集合作为视图
         　　4.keys视图返回的是个Multiset，这个Multiset是以不重复的键对应的个数作为视图。这个Multiset可以通过支持移除操作而不是添加操作来修改Multimap。
         　　5.values()视图能把Multimap里的所有值“平展”成一个Collection<V>。这个操作和Iterables.concat(multimap.asMap().values())很相似，只是它返回的是一个完整的Collection。
         */

        /**
         * 尽管Multimap的实现用到了Map，但Multimap<K, V>不是Map<K, Collection<V>>。因为两者有明显区别：
         　　1.Multimap.get(key)一定返回一个非null的集合。但这不表示Multimap使用了内存来关联这些键，相反，返回的集合只是个允许添加元素的视图。
         　　2.如果你喜欢像Map那样当不存在键的时候要返回null，而不是Multimap那样返回空集合的话，可以用asMap()返回的视图来得到Map<K, Collection<V>>。（这种情况下，你得把返回的Collection<V>强转型为List或Set）。
         　　3.Multimap.containsKey(key)只有在这个键存在的时候才返回true。
         　　4.Multimap.entries()返回的是Multimap所有的键值对。但是如果需要key-collection的键值对，那就得用asMap().entries()。
         　　5.Multimap.size()返回的是entries的数量，而不是不重复键的数量。如果要得到不重复键的数目就得用Multimap.keySet().size()。
         */


        /**
         * Multimap的实现

         　　Multimap提供了丰富的实现，所以你可以用它来替代程序里的Map<K, Collection<V>>，具体的实现如下：
         　　Implementation            Keys 的行为类似       　　　Values的行为类似
         　　ArrayListMultimap         HashMap                   　　ArrayList
         　　HashMultimap              HashMap                  　　 HashSet
         　　LinkedListMultimap        LinkedHashMap*               LinkedList*
         　　LinkedHashMultimap        LinkedHashMap                LinkedHashSet
         　　TreeMultimap              TreeMap                      TreeSet
         　　ImmutableListMultimap     ImmutableMap                 ImmutableList
         　　ImmutableSetMultimap      ImmutableMap                 ImmutableSet


         以上这些实现，除了immutable的实现都支持null的键和值。
         　　1.LinkedListMultimap.entries()能维持迭代时的顺序。
         　　2.LinkedHashMultimap维持插入的顺序，以及键的插入顺序。
         　　要注意并不是所有的实现都正真实现了Map<K, Collection<V>>！（尤其是有些Multimap的实现为了最小话开销，使用了自定义的hash table）
         */
    }

    @Test
    public void testMap(){
        Multimap<String,Object> maps = ArrayListMultimap.create();
        maps.put("aa","bb");
        maps.put("aa","c");
        maps.forEach((key,value)->{
            System.out.println(key+"->"+value);
        });
    }

    @Test
    public void testFiles(){
        File file = new File("E:\\二代核心所有系统");
        if(file.isDirectory()){
            for(File f : file.listFiles()){

            }
        }
    }
}
