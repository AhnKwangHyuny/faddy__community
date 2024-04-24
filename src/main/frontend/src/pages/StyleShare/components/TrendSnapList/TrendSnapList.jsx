import {ImageCarouselWithSnapCards} from "widgets/Carousel/Carousel";

function TrendingSnapCard() {

    const settings = {
        dots: false,
        infinite: true,
        speed: 700,
        slidesToShow: 2,
        slidesToScroll: 1,
        arrows: false,
        draggable: true, // enable drag and drop
        // swipeToSlide: true, // enable swipe gesture
        initialSlide: 1,
        autoplay: true,
        autoplaySpeed: 3000,
    };

    const snapDataList = [
        {
            username: 'user1',
            imageUrl: 'https://example.com/image1.jpg',
            ranking: 1,
        },
        {
            username: 'user2',
            imageUrl: 'https://example.com/image2.jpg',
            ranking: 2,
        },
        {
            username: 'user3',
            ranking: 3,
        },
        {
            username: 'user4',
            imageUrl: 'https://example.com/image4.jpg',
            ranking: 4,
        },
        {
            username: 'user5',
            imageUrl: 'https://example.com/image5.jpg',
            ranking: 5,
        },
        {
            username: 'user6',
            ranking: 6,
        },
        {
            username: 'user7',
            imageUrl: 'https://example.com/image7.jpg',
            ranking: 7,
        },
    ];

    return (
        <section className="trend-snap-card-list">

            <div className="title">
                <div className="title-wrapper">
                    <div className="title__name">
                        <span className="name">
                            위클리 트랜드
                        </span>
                    </div>
                </div>
            </div>

            <div className = "trend-snap-list">
                <ImageCarouselWithSnapCards settings={settings} snapDataList={snapDataList}/>
            </div>
        </section>
    );

};


export default TrendingSnapCard;
