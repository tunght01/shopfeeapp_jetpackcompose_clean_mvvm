package com.example.shopfeeap_mvvm.presentation.home_creen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.shopfeeap_mvvm.R
import com.example.shopfeeap_mvvm.data.local.extention.FomartUtility
import com.example.shopfeeap_mvvm.data.local.extention.textFileWhite
import com.example.shopfeeap_mvvm.data.local.extention.textFileWhiteBold
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navController: NavHostController, modifier: Modifier = Modifier,
//    onClickDetailScreen:(Drink)->Unit, onClick: () -> Unit
){

//    val context = LocalContext.current
//    val storeUser = StoreUser(context)
//    var user by remember { mutableStateOf<User?>(null) }
//    LaunchedEffect(Unit) {
//        user = storeUser.fetchUser()!! // Gọi hàm fetchEmail()
//
//    }
//    val detailCartViewModel: DetailCartViewModel = viewModel()
//    LaunchedEffect(Unit) {
//        if (user!=null){
//            detailCartViewModel.setUsername(user!!.username)
//        }
//
//    }

    val image = listOf(
        R.drawable.imageslide3,
        R.drawable.imageslide2,
        R.drawable.imageslide1,
    )

    Column {
        Column (modifier = Modifier.weight(1f)){
            OutlinedTextField(
                value = "",
                onValueChange = {},
                trailingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .padding()
            )
            ImageSliderWithIndicator(images = image)
            RecipeScreen()

        }



    }

}

@Composable
fun ImageSliderItem(imageRes:Int){

    Image(painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)


    )
}

@Composable
fun ImageSliderWithIndicator(images: List<Int>){
    val currentIndex = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(Unit) {
        while (true){
            delay(3000)
            currentIndex.value = (currentIndex.value +1) % images.size
        }

    }
    ConstraintLayout() {
        val (image,indicator) = createRefs()
        Box (
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(image) {
                    start.linkTo(parent.start)
                }
        ){
            ImageSliderItem(imageRes = images[currentIndex.value])
        }
        Row (modifier = Modifier.constrainAs(indicator){
            start.linkTo(image.start, margin = 2.dp)
            bottom.linkTo(image.bottom, margin = 5.dp)
            end.linkTo(image.end)

        }){
            images.forEachIndexed{
                    index, i ->
                Indicator(active = index == currentIndex.value)
                if (index < images.size -1){
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }

        }


    }
    Column(modifier = Modifier.fillMaxWidth()) {



    }
}
@SuppressLint("ResourceAsColor")
@Composable
fun Indicator(active:Boolean){
    val color = if(active) Color(R.color.brown) else Color.White
    val size = if (active) 10.dp else 10.dp
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .size(size)) {

    }

}

@Composable
fun FooterHomeScreen(totalAmout:Int,nameDrink:String,totalPrice:Int,onClick:()->Unit){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .background(
            color = Color("#5D4037".toColorInt()),
            shape = RoundedCornerShape(5.dp)
        )
        .size(60.dp).clickable {
            onClick()
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){

        Column (modifier = Modifier
            .padding(10.dp)){
            textFileWhite(title = "$totalAmout đồ uống")
            Spacer(modifier = Modifier.height(10.dp))
            textFileWhite(title = nameDrink)

        }


        Row (modifier = Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically){
            textFileWhiteBold(title = FomartUtility().format(totalPrice))
            Spacer(modifier = Modifier.width(5.dp))
            Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color.White)

        }

    }
}