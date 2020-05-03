package com.cbsinc.cms.dto.pages;

import java.util.List;

import com.cbsinc.cms.dto.Admin;
import com.cbsinc.cms.dto.Bottom;
import com.cbsinc.cms.dto.CatalogItem;
import com.cbsinc.cms.dto.Creteria1Item;
import com.cbsinc.cms.dto.CurrenciesItem;
import com.cbsinc.cms.dto.DayFromItem;
import com.cbsinc.cms.dto.DayToItem;
import com.cbsinc.cms.dto.MenuItem;
import com.cbsinc.cms.dto.MountFromItem;
import com.cbsinc.cms.dto.MountToItem;
import com.cbsinc.cms.dto.News;
import com.cbsinc.cms.dto.ParentItem;
import com.cbsinc.cms.dto.Product;
import com.cbsinc.cms.dto.ProductBlog;
import com.cbsinc.cms.dto.XslStyle;
import com.cbsinc.cms.dto.YearFromItem;
import com.cbsinc.cms.dto.YearToItem;

public class CMSMainPageModel {
	
	
String version ;
String name ;
String title ;
String reklama ;
String subject_site ;
String site_name ;
String host ;
String message ;
String login ;
String passwdord ;
String balans ;
String search_value ;
String search_query ;
String fromcost ;
String tocost ;
String owner_user_id ;
String role_id ;
String user_site_id;
String site_id ;
String path ;
String dialog ;
String is_advanced_search_open ;
String is_forum_open ;
String internet ;
Admin admin;
XslStyle xslStyle;

List<Product> product_list ;

List<Product> coproductlist1 ;

List<Product> coproductlist2 ;

List<ProductBlog> product_blog_list;

List<News>newslist;

List<Bottom>bottomlist;

String empty_page_co1 ;

String empty_page_co2 ;

String empty_page ;

String quantity_products ;

String offset ;
String next ;
String prev ;

String criteria1_label ;
String criteria2_label ;
String criteria3_label ;
String criteria4_label ;
String criteria5_label ;
String criteria6_label ;
String criteria7_label ;
String criteria8_label ;
String criteria9_label ;
String criteria10_label ;

List<CurrenciesItem> currencies ;
List<CatalogItem>catalog;
List<MenuItem>menu;

List<Creteria1Item> creteria1 ;
List<Creteria1Item> creteria2;
List<Creteria1Item> creteria3;
List<Creteria1Item> creteria4;
List<Creteria1Item> creteria5;
List<Creteria1Item> creteria6;
List<Creteria1Item> creteria7;
List<Creteria1Item> creteria8;
List<Creteria1Item> creteria9;
List<Creteria1Item> creteria10;

List<DayFromItem>  dayFrom ;
List<MountFromItem>  mountFrom;
List<YearFromItem>  yearFrom;

List<DayToItem>  dayTo;
List<MountToItem>  mountTo;
List<YearToItem>  yearTo;

List<ParentItem>  parent;

}
