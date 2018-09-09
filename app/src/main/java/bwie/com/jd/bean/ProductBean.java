package bwie.com.jd.bean;

import java.util.List;

public class ProductBean {
        public String msg;
        public String code;
        public List<Product> data;

        public class Product{
            public String title;
            public String images;
        }
}
