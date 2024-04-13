import HighlightSnapCard from "widgets/HighlightSnapCard/HighlightSnapCard";

function TrendingSnapCard() {

    return (
        <>
            {/*<Swiper*/}
            {/*    spaceBetween={50}*/}
            {/*    slidesPerView={3}*/}
            {/*    onSlideChange={() => console.log('slide change')}*/}
            {/*    onSwiper={(swiper) => console.log(swiper)}*/}
            {/*>*/}
            {/*    <SwiperSlide>Slide 1</SwiperSlide>*/}
            {/*    <SwiperSlide>Slide 2</SwiperSlide>*/}
            {/*    <SwiperSlide>Slide 3</SwiperSlide>*/}
            {/*    <SwiperSlide>Slide 4</SwiperSlide>*/}
            {/*    ...*/}
            {/*</Swiper>*/}

            <div className = "trending__snap__list">
                <div classNmae = "title__container">
                    <div className="title">

                    </div>
                </div>


                <HighlightSnapCard/>
            </div>
        </>
    );

};


export default TrendingSnapCard;
