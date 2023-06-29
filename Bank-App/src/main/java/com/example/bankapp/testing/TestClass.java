package com.example.bankapp.testing;
import com.example.bankapp.model.BankAppCurrency;
import com.example.bankapp.utility.ExchangeCurrency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class TestClass {

    public static void main(String[] args) throws IOException {



        //Objectmapper
        //

//
//        boolean test = StringCheck.ValidString("55,5f",3);
//        System.out.println(test);

//        System.out.println(bcrypt.hashedPassword);
//
//        bcrypt.checkPass("HundKatzeMaus");
//
//
//
//
//
////        BCryptPasswordEncoder test = new  BCryptPasswordEncoder(16);
        //"$2a$10$772elZBmWsQYJwYNxHdDo.74rn1oUlpC/1rzw0brbCAnr7nNtI4aO"
//
//
//      Iterator<Wallet> walletIterator = getLoadedWallets().listIterator();
//
//
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankAppDBjpa");
//        EntityManager em = emf.createEntityManager();
//        UserRepositoryJPA userRepository = new UserRepositoryJPA();
//        WalletRepositoryJPA walletRepository = new WalletRepositoryJPA();
//        TransactionRepositoryJPA transactionRepository = new TransactionRepositoryJPA();



                // Wie kann man mit Jackson Objekte zu JSON serialisieren?
//                Student testStudent = new Student(1l, "Johannes", "Priebsch",
////                        LocalDate.of(1983, 12, 27), List.of(Course.DATA_SCIENCE, Course.LITERATURE));
//                URL url = new URL("http://www.floatrates.com/daily/usd.json");
//
//                String testString1 = "{\"eur\":{\"code\":\"EUR\",\"alphaCode\":\"EUR\",\"numericCode\":\"978\",\"name\":\"Euro\",\"rate\":0.92260858763772,\"date\":\"Wed, 17 May 2023 11:55:01 GMT\",\"inverseRate\":1.0838832560191}}";


                URL url = new URL("http://www.floatrates.com/daily/EUR.json");
        System.out.println(ExchangeCurrency.isReachable(url.toString()));
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String allLines = reader.lines().reduce("", (a,b)-> a + b);
//
//
//
//
//
//                ObjectMapper jsonMapper = new ObjectMapper();
////
//////                jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////                // Modul registrieren da java.time.LocalDate verwendet
////
//                jsonMapper.registerModule(new JavaTimeModule());
////
////                jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
////                jsonMapper.readValue(testString1, JsonOuter.class );
////                JsonOuter test = jsonMapper.readValue(testString1,JsonOuter.class);
////                JsonInner i = test.eur;
////        System.out.println(i.rate);
////
//        HashMap<String,JsonInner> testMap = new ObjectMapper().readValue(allLines, new TypeReference<HashMap<String, JsonInner>>() {
//        });
//        System.out.println(testMap);
//        JsonInner eh = testMap.get("eur");
//        String stringTest = testMap.get("eur").rate;
//        System.out.println(eh);
////        System.out.println(stringTest);
//
//        BigDecimal test = ExchangeCurrency.toTargetRateJSON(BankAppCurrency.EUR,BankAppCurrency.USD);
//        System.out.println(test);
////
//        System.out.println(testMap);
//        System.out.println(testMap.get("eur").getInner().rate);
                //                jsonMapper.readValue(allLines, new TypeReference<Map<String,JsonInner>>() {
//                });
//                Map<String, String> test2 = new HashMap<String,String>();
//                test2 = jsonMapper.readValue(allLines, new TypeReference<Map<String, String>>() {
//                });
//                Map<String,JsonInner> test1 = jsonMapper.readValue(allLines, new TypeReference<Map<String, JsonInner>>() {
//                });
//        System.out.println(test1.get("eur"));
//              Map<String,JsonInner> test1 = jsonMapper.readValue(allLines, new TypeReference<Map<String, JsonInner>>() {});
//        System.out.println(test1.get("eur"));
//               List<JsonOuter> jsonObjects = List.of(jsonMapper.readValue(allLines,JsonOuter.class));


//
//        JsonParser jp = new JsonParser();
//        JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
//        System.out.println(root);
//
//        Gson gson = new Gson();
//        RateJSONClass rateJSONClass=null;
//        rateJSONClass = gson.fromJson(root, RateJSONClass.class);
//        System.out.println(rateJSONClass.inner);
//
//        System.out.println("ROOT:**************************************************");
//        System.out.println(root);
//
//        JsonObject rootAsJsonObject = root.getAsJsonObject();

//        JsonObject rootobj = root.getAsJsonObject();
//
//        System.out.println("ROOTOBJ:************************************************");
//        System.out.println(arr);
////
//
////
//        System.out.println(arr);

//        String test = "33.4";
//        System.out.println(test);
//        test=test.replace(',','.');
//        System.out.println(test);
//        BigDecimal bigDecimal = new BigDecimal(test);
//        System.out.println(bigDecimal);

//
//        Transaction test = new Transaction(LocalDate.now(),new BigDecimal("20.0"),EUR);
//        System.out.println(test.getSender());
//

//        Optional<User> testUser = userRepository.readByLoginData(1,"ad");
//        System.out.println(testUser);
//
//        if (!(Optional.ofNullable(testUser).isPresent())){
//            System.out.println(".isEmpty() = true");
//        }
//        List<Wallet> test =  walletRepository.readByUserID(1);
//        Iterator<Wallet> walletIterator = test.listIterator();
//
//        while (walletIterator.hasNext()){
//
//            Wallet wallet = walletIterator.next();
//            System.out.println(wallet.toString());
//
//
//        }


//

//        System.out.println(test.toString());
//
//
//        User testUser = userRepository.readByLoginData(1,"admin");
//        System.out.println(testUser.toString());


//        List<Transaction> testList = transactionRepository.readAllTransactionByUser(1);
//        System.out.println(testList.toString());

//        boolean test = true;
//        BankAppCurrency test2 = EUR;
//
//
//        Wallet testWallet = walletRepository.readByIDandCurrency(2, test2);
//        System.out.println(testWallet.toString());
//
//        List<Wallet> wallets = walletRepository.readByUserID(1);
//        System.out.println(wallets.toString());


//        Optional<User> testUser = userRepository.readById(1);
//
//        System.out.println(testUser.toString());
//
//        User notDavid = new User(3,"notDavid","notPriocic","admin","1234");
//        userRepository.delete(notDavid);
//
//        testUser = userRepository.readById(1);
//
//        System.out.println(testUser.toString());



//        List<User> users = userRepository.readAll();
//        System.out.println(users.toString());
//        User test = users.get(0);
//        System.out.println(test);




//        String url = "jdbc:mariadb://localhost:3306/bank_app_db";
//        String username = "root";
//        String password = "";
//        String query = "select * from user";
//
//        Connection connection = null;
//
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            connection = DriverManager.getConnection(url,username,password);
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(query);
//
//            while (result.next()){
//                String userData = "";
//                for (int i = 1;i <= 5;i++){
//                    userData += result.getString(i) + ":";
//                }
//                System.out.println(userData);
//            }
//
//
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }




//        Query for loading User password as String
//        Query for loading User firstName + User lastName as Strings
//        Query for loading a User's wallet value + wallet currency as Strings
//        Query for loading all of User's wallet value + wallet currency in List<String>
//        Query for loading all of User's Transaction ID + dateOfTransaction +
//          sender first+last name (by ID) + receiver first+last name (by ID) + value + currency as List<String>
//        Query for updating a User's wallet value with a provided value
//        Query for creating a new transaction with transaction ID + dateOfTransaction +  sender ID + receiver ID + value + currency
//          as List<String>(?)






    }
}
