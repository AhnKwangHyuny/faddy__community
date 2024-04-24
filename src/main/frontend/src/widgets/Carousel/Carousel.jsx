import React, {useCallback, useState} from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import HighlightSnapCard from "widgets/HighlightSnapCard/HighlightSnapCard";

function ImageCarouselWithComponents({ settings, componentList }) {
    const defaultSettings = {
        dots: true,
        infinite: true,
        speed: 600,
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        ...settings,
    };

    return (
        <section className="carousel">
            <Slider {...defaultSettings} className="mp-0">
                {componentList.map((Component, index) => (
                    <div className="image" key={`component-${index}`}>
                        <div className="image__wrapper">
                            <Component />
                        </div>
                    </div>
                ))}
            </Slider>
        </section>
    );
}

function ImageCarouselWithHTML({ settings, htmlList }) {
    const defaultSettings = {
        dots: true,
        infinite: true,
        speed: 600,
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        ...settings,
    };

    return (
        <section className="carousel">
            <Slider {...defaultSettings} className="mp-0">
                {htmlList.map((item, index) => (
                    <div className="image" key={index}>
                        <div className="image__wrapper">{item}</div>
                    </div>
                ))}
            </Slider>
        </section>
    );
}

function ImageCarouselWithSnapCards({ settings, snapDataList }) {
    const [dragging, setDragging] = useState(false);

    const handleBeforeChange = useCallback(() => {
        setDragging(true);
    }, [setDragging]);

    const handleAfterChange = useCallback(() => {
        setDragging(false);
    }, [setDragging]);



    const defaultSettings = {
        dots: true,
        infinite: true,
        speed: 600,
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        ...settings,
    };

    return (
        <section className="carousel">
            <Slider beforeChange={handleBeforeChange}
                    afterChange={handleAfterChange}
                    {...defaultSettings}
                    className="mp-0"
            >
                {snapDataList.map(({ username, imageUrl, ranking }, index) => (
                    <div className="image" key={`component-${index}`}>
                        <div className="image__wrapper">
                            <HighlightSnapCard username={username} imageUrl={imageUrl} ranking={ranking} />
                        </div>
                    </div>
                ))}
            </Slider>
        </section>
    );
}

export { ImageCarouselWithComponents, ImageCarouselWithHTML ,  ImageCarouselWithSnapCards};