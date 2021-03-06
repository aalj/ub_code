//package net.lll0.bus;
//
//import de.greenrobot.daogenerator.DaoGenerator;
//import de.greenrobot.daogenerator.Entity;
//import de.greenrobot.daogenerator.Property;
//import de.greenrobot.daogenerator.Schema;
//
///**
// *
// */
//public class MyDaoGenerator {
//
//    public static void main(String[] args) throws Exception {
//        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
//        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
//        Schema schema = new Schema(1, "net.lll0.bus.database");
////      当然，如果你愿意，你也可以分别指定生成的 Bean 与 DAO 类所在的目录，只要如下所示：
////      Schema schema = new Schema(1, "me.itangqi.bean");
////      schema.setDefaultJavaPackageDao("me.itangqi.dao");
//
//        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
//        // schema2.enableActiveEntitiesByDefault();
//        // schema2.enableKeepSectionsByDefault();
//
//        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
//        addNote(schema);
//
//
//        // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
//        // 其实，输出目录的路径可以在 build.gradle 中设置，有兴趣的朋友可以自行搜索，这里就不再详解。
//        new DaoGenerator().generateAll(schema,"E:\\workspace\\ub_code\\android\\idea\\SuZhouBus\\app\\src\\main\\java");
////        schema = new Schema(1, "me.itangqi.greendao");
////        addNote2(schema);
////        new DaoGenerator().generateAll(schema, "D:\\my_owe_code\\android\\Android_Studio\\MyGreenDAO\\app\\src\\main\\java-gen");
//    }
//
//    /**
//     * @param schema
//     */
//    private static void addNote(Schema schema) {
//        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Note」（既类名）
//        Entity note = schema.addEntity("CouponInfo");
//        // 你也可以重新给表命名
//        // note.setTableName("NODE");
//
//        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
//        // 接下来你便可以设置表中的字段：
////        note.addIdProperty();
//        Property.PropertyBuilder builder = note.addLongProperty("_id");
//        builder.columnName("_id").primaryKey();
//
////        note.addStringProperty("ID").notNull();
//        // 与在 Java 中使用驼峰命名法不同，默认数据库中的命名是使用大写和下划线来分割单词的。
//        // For example, a property called “creationDate” will become a database column “CREATION_DATE”.
//        note.addStringProperty("id").unique();
//        note.addStringProperty("price");
//        note.addStringProperty("message");
//        note.addStringProperty("coupon_code");
//        note.addStringProperty("active_time");
//        note.addStringProperty("expire_time");
//        note.addStringProperty("is_deprecated");
//        note.addStringProperty("is_used");
//        note.addStringProperty("used_time");
//        note.addStringProperty("used_user_id");
//
//        /**
//         * "id": "181885f2ead24e3a9aa6b11d06d688a8",
//         "price": "50.00",
//         "message": "181885f2ead24e3a9aa6b11d06d688a8福库劵可用",
//         "coupon_code": "4757011b01ad4e1395a91fb74dedb0b3",
//         "active_time": 1483632000000,
//         "expire_time": 1483718399000,
//         "is_deprecated": 0,
//         "is_used": 0,
//         "used_time": 0,
//         "used_user_id": null
//         */
//
//
//
//
//
////        Entity note2 = schema.addEntity("Note2");
////        // 你也可以重新给表命名
////        // note.setTableName("NODE");
////
////        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
////        // 接下来你便可以设置表中的字段：
////        note2.addIdProperty();
////        note2.addStringProperty("text").notNull();
////        // 与在 Java 中使用驼峰命名法不同，默认数据库中的命名是使用大写和下划线来分割单词的。
////        // For example, a property called “creationDate” will become a database column “CREATION_DATE”.
////        note2.addStringProperty("comment");
////        note2.addDateProperty("date");
//
//    }
//}
