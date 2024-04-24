import random
import textwrap

# 日本人女性の名前のサンプルリスト (アルファベット表記)
japanese_female_names = [
    "Ai", "Yui", "Rio", "Saki", "Moe", "Yuna", "Aoi", "Miyu", "Kana", "Eri",
    "Hina", "Mei", "Nana", "Sakura", "Haruka", "Rina", "Asuka", "Yuki", "Kaho", "Riko"
]

# 星座と血液型のサンプル
zodiac_signs = ["Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"]
blood_types = ["A", "B", "O", "AB"]

# 自己紹介と推薦文のサンプル生成関数
def generate_intro():
    return " ".join(random.sample(textwrap.wrap(
        "Hello! My name is [name]. I love to spend time outdoors and enjoy hiking and picnicking. I am also passionate about music and play the piano in my free time. I am looking forward to working with new people and making unforgettable memories!",
        100), 1))

def generate_recommendation():
    return " ".join(random.sample(textwrap.wrap(
        "[name] is an outstanding performer with a warm personality that captivates everyone. Her dedication and passion for the arts are evident in her every move. She is a team player who lifts the spirits of all around her. We highly recommend her to any visitor looking for a remarkable experience.",
        100), 1))

# 各ショップに対するランダムなデータを生成
shops = 6  # ショップの数
data = []

for shop_id in range(1, shops + 1):
    num_casts = random.randint(15, 30)  # 各ショップに15〜30人のキャスト
    for _ in range(num_casts):
        name = random.choice(japanese_female_names)
        age = random.randint(20, 34)
        height = random.randint(150, 180)
        size_bust = random.randint(75, 100)
        size_waist = random.randint(50, 80)
        size_hips = random.randint(75, 105)
        zodiac_sign = random.choice(zodiac_signs)
        blood_type = random.choice(blood_types)
        cast_comment = generate_intro().replace("[name]", name)
        shop_comment = generate_recommendation().replace("[name]", name)
        data.append((name, age, height, size_bust, size_waist, size_hips, zodiac_sign, blood_type, shop_id, True, cast_comment, shop_comment))

# SQL文の形式で出力
sql_values_full = [f"('{name}', {age}, {height}, {size_bust}, {size_waist}, {size_hips}, '{zodiac_sign}', '{blood_type}', {shop_id}, {enabled}, '{cast_comment}', '{shop_comment}')" for name, age, height, size_bust, size_waist, size_hips, zodiac_sign, blood_type, shop_id, enabled, cast_comment, shop_comment in data]
sql_query_full = "INSERT INTO casts\n(name, age, height, size_bust, size_waist, size_hips, zodiac_sign, blood_type, shop_id, enabled, cast_comment, shop_comment)\nVALUES\n" + ',\n'.join(sql_values_full) + ';'

print(sql_query_full)
