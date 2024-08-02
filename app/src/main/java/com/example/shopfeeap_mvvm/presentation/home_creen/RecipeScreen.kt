package com.example.shopfeeap_mvvm.presentation.home_creen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import com.example.shopfeeap_mvvm.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel

import coil.compose.rememberAsyncImagePainter
import com.example.shopfeeap_mvvm.common.Constant
import com.example.shopfeeap_mvvm.data.local.extention.FomartUtility
import com.example.shopfeeap_mvvm.domain.model.drink.DrinkModel
import com.example.shopfeeap_mvvm.presentation.home_creen.viewmodel.DrinksViewModel
import java.text.DecimalFormat


@Composable
fun RecipeScreen(
//    navHostController: NavHostController,
    modifier: Modifier = Modifier,
//    onClickDateil: (Drink) -> Unit
){
    val viewModel: DrinksViewModel = hiltViewModel()
    val state by viewModel.state
    Box(modifier = Modifier
        .fillMaxSize()
        ){
        CategoryScreen(drinks = state.drinks)
        if (state.error.isNotBlank()){
            Text(text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center))
        }
        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }


}


// How each Items looks like

@Composable
fun CategoryScreen(
    drinks: List<DrinkModel>,
//    onClickDateil: () -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(drinks) { drink ->

            CategoryItem(drink = drink){
                Log.e("tung","CategoryScreen $it")
//                onClickDateil(it)
            }
        }
    }


}

//@Composable
//fun FilterDrink() {
//    val listFilterDrink = listOf(
//        "Tất cả" to Icons.Filled.SelectAll,
//        "Xếp hạng" to Icons.Filled.StarOutline,
//        "Giá" to Icons.Filled.AttachMoney,
//        "Khuyến mại" to Icons.Filled.LocalOffer
//    ) // Ánh xạ filter với icon
//
//    LazyRow(
//        horizontalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(horizontal = 10.dp)
//    ) {
//        items(listFilterDrink) { (filter, icon) -> // Lấy cả filter và icon
//            FilterDrinkItem(filter, icon) // Truyền cả filter và icon vào FilterDrinkItem
//        }
//    }
//}

@Composable
fun FilterDrinkItem(filter: String, icon: ImageVector) { // Thêm tham số icon
    Row(
        modifier = Modifier
            .background(color = Color.Gray.copy(alpha = 0.2f), shape = CircleShape)
            .border(
                BorderStroke(width = 1.dp, color = Color.Gray),
                shape = CircleShape
            )
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .clickable { /* Xử lý khi click */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon, // Sử dụng icon được truyền vào
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = filter)
    }
}


//@Composable
//fun CategoryItem(drink: Drink) {
//    Column(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth()
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.images1),
//            contentDescription = null,
//            modifier = Modifier.fillMaxWidth() // Make image fill width
//        )
//
//        Row(  // Rating row below image
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 4.dp), // Add padding above rating
//            horizontalArrangement = Arrangement.Center // Center rating horizontally
//        ) {
//            Text(text = "5")
//            Image(painter = painterResource(id = R.drawable.star), contentDescription = "star")
//        }
//
//        Text(
//            text = drink.name,
//            color = Color.Black,
//            style = TextStyle(fontWeight = FontWeight.Bold),
//            modifier = Modifier.padding(top = 4.dp),
//            textAlign = TextAlign.Center // Center text horizontally
//        )
//    }
//}
@Composable
fun CategoryItem(
    drink: DrinkModel,
    onClickDateil:(DrinkModel) ->Unit
) {
    var checkSale = false
    if (drink.sales!! > 0){
        checkSale = true
    }


    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                Log.e("tung", "item ${drink}")
                onClickDateil(drink)
            }
    ) {
        val (image, ratingText, starIcon, nameText, descriptionText, priceText,priceDiscount, boxRate) = createRefs()

        Image(
            painter = rememberAsyncImagePainter(Constant.BASE_URL+drink.ImageUrl?.url),
            contentDescription = null,

            modifier = Modifier
                .size(50.dp) // Adjust size as needed
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop,
        )

        ConstraintLayout(
            modifier = Modifier
                .padding(3.dp)
                .clip(shape = RoundedCornerShape(8.dp)) // Khoảng cách từ nội dung đến viền Box
                .background(color = Color.White)
                .constrainAs(boxRate) {
                    top.linkTo(image.top, margin = 47.dp)
                    start.linkTo(image.start)
                    end.linkTo(image.end, margin = 3.dp)
                    bottom.linkTo(image.bottom)
                }
                .wrapContentSize()// Màu nền trắng,


        ) {
            Text(
                text = FomartUtility().calculateAverageRating(drink.rates).toString(),
                color = Color.Black,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier.constrainAs(ratingText) {
                    top.linkTo(image.top, margin = 70.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
//                    .align(Alignment.CenterStart)
//                    .padding(horizontal = 8.dp, vertical = 4.dp) // Khoảng cách từ nội dung đến viền
            )
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Star",
                modifier = Modifier
                    .size(15.dp)
                    .constrainAs(starIcon) {
                        top.linkTo(ratingText.top)
                        start.linkTo(ratingText.end, margin = 2.dp)
                    }
                    .padding(end = 5.dp)
            )
        }

        Text(
            text = "${drink.name!!}",
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp),
            modifier = Modifier.constrainAs(nameText) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(image.end, margin = 8.dp)
                end.linkTo(priceText.start, margin = 8.dp) // Link to the start of priceText
                width = Dimension.fillToConstraints // Make text fill within constraints
            }
        )

        Text(
            text = drink.Description!!,
            style = TextStyle(fontSize = 10.sp),
            color = Color.Black,
            modifier = Modifier.constrainAs(descriptionText) {
                top.linkTo(nameText.bottom, margin = 8.dp)
                start.linkTo(image.end, margin = 8.dp)
                end.linkTo(priceText.start, margin = 8.dp) // Link to the start of priceText
                width = Dimension.fillToConstraints // Make text fill within constraints
            }
        )
        Text(
            text = if (checkSale) {
                val discountedPrice = drink.Price!! - (drink.Price / drink.sales)
                FomartUtility().format(price = discountedPrice)
            } else {
                "" // If sales are not greater than 0, display an empty string or some default text
            },
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 10.sp),
            modifier = Modifier.constrainAs(priceDiscount) {
                top.linkTo(nameText.top)
                end.linkTo(parent.end, margin = 8.dp) // Keep price text on the right
            }
        )


        Text(
            text = DecimalFormat("#,### VNĐ").format(drink.Price),
            color = Color.Black,
            style = TextStyle(fontWeight = if (checkSale) FontWeight.Normal else FontWeight.Bold, fontSize = 10.sp,
                textDecoration = if (checkSale) TextDecoration.LineThrough else TextDecoration.None ,
            ),
            modifier = Modifier.constrainAs(priceText) {
                top.linkTo(descriptionText.top)
                end.linkTo(parent.end, margin = 8.dp) // Keep price text on the right
            }
        )
    }
}
@Preview( showBackground = true, showSystemUi = true
)
@Composable
fun PreViewRecipe(){
    RecipeScreen()

}