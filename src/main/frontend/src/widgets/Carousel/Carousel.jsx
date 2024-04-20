import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
function ImageCarousel() {
    const settings = {
        dots: true,
        infinite: true,
        speed: 600,
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows:false,
    };
    return (
        <section className="carousel">
            <Slider {...settings} className="mp-0">
                <div className="image">
                    <div className="image__wrapper">
                        <img src="/default_profile.jpg" alt="default image"/>
                    </div>
                </div>

                <div className="image">
                    <div className="image__wrapper">
                        <img src="/default_profile.jpg" alt="default image"/>
                    </div>
                </div>

                <div className="image">
                    <div className="image__wrapper">
                        <img src="/default_profile.jpg" alt="default image"/>
                    </div>
                </div>

                <div className="image">
                    <div className="image__wrapper">
                        <img src="/default_profile.jpg" alt="default image"/>
                    </div>
                </div>

                <div className="image">
                    <div className="image__wrapper">
                        <img src="/default_profile.jpg" alt="default image"/>
                    </div>
                </div>

                <div className="image">
                    <div className="image__wrapper">
                        <img src="/default_profile.jpg" alt="default image"/>
                    </div>
                </div>
            </Slider>
        </section>
    );
}

export default ImageCarousel;